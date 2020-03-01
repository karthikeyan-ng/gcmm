package com.techstack.gcmm.formulary;

import com.techstack.gcmm.controller.api.PurchaseOrderInfo;
import com.techstack.gcmm.exception.CalculationException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class provides business functionality related to Global Collateral and
 * Margin Management related calculation formulas
 * 
 * The below class is 1:1 representation of the given formula specification.
 * 
 * For better readability, all the public methods are starts with "calculate"
 * prefix followed by corresponding formula name.
 * 
 * @author Karthikeyan N
 *
 */
@Service
public class CommonFormulary {

	/**
	 * Given any price as the input, please calculate the Revenue yield
	 * 
	 * @param purchaseOrderInfo
	 * @return BigDecimal
	 * @throws CalculationException
	 */
	public BigDecimal calculateRevenueYield(@NotNull final PurchaseOrderInfo purchaseOrderInfo)
			throws CalculationException {

		if (purchaseOrderInfo.getPrice() <= 0d)
			throw new CalculationException("Purchase Order Price Should not be Zero or Negative");

		Predicate<OilSpecification> checkOilTypeIsStandard = o -> o.getType()
				.equals(OilSpecification.OIL_TYPE_STANDARD);
		Predicate<OilSpecification> checkOilTypeIsPremium = o -> o.getType().equals(OilSpecification.OIL_TYPE_PREMIUM);

		double sumForStandardOilType = Stream.of(OilSpecification.values()).filter(checkOilTypeIsStandard)
				.mapToDouble(oilId -> calculateRevenueYieldForStandard(oilId, purchaseOrderInfo.getPrice())).sum();

		double sumForPremiumOilType = Stream.of(OilSpecification.values()).filter(checkOilTypeIsPremium)
				.mapToDouble(oilId -> calculateRevenueYieldForPremium(oilId, purchaseOrderInfo.getPrice())).sum();

		BigDecimal revenueYield = BigDecimal.valueOf(sumForStandardOilType)
				.add(BigDecimal.valueOf(sumForPremiumOilType));

		return revenueYield;

	}

	/**
	 * Calculate Revenue Yield for Standard Oil Type
	 * 
	 * @param oilId
	 * @param price
	 * @return double
	 * @throws CalculationException
	 */
	private double calculateRevenueYieldForStandard(OilSpecification oilId, final double price)
			throws CalculationException {

		Double revenueYield = null;

		switch (oilId) {
		case AAC:
		case REW:
		case BWO:
		case QFC:
			revenueYield = oilId.getFixedRevenue() / price;
			break;
		}

		return revenueYield.doubleValue();
	}

	/**
	 * Calculate Revenue Yield for Premium Oil Type
	 * 
	 * @param oilId
	 * @param price
	 * @return double
	 * @throws CalculationException
	 */
	private double calculateRevenueYieldForPremium(OilSpecification oilId, double price) throws CalculationException {
		Double revenueYield = null;

		switch (oilId) {
		case TIM:
			revenueYield = (oilId.getVariableRevenue() * oilId.getFixedRevenue()) / price;
			break;
		}
		return revenueYield.doubleValue();
	}

	/**
	 * Given any price as the input, please calculate the Price-Earnings Ratio
	 * 
	 * @param revenue
	 * @param purchaseOrderInfo
	 * @return
	 * @throws CalculationException
	 */
	public BigDecimal calculatePriceEarningRatio(final BigDecimal revenue, final PurchaseOrderInfo purchaseOrderInfo)
			throws CalculationException {

		if (revenue.compareTo(BigDecimal.ZERO) == 0)
			throw new CalculationException("Revenue is 0, hence It will cause ArithmeticException: / by zero");

		return BigDecimal.valueOf(purchaseOrderInfo.getPrice()).divide(revenue, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * Calculate Volume Weighted Oil Price based on transaction in the past 30
	 * minutes.
	 * 
	 * @param purchaseOrderInfos
	 * @return BigDecimal
	 * @throws CalculationException
	 */
	public BigDecimal calculateVolumeWeightedAverage(final List<PurchaseOrderInfo> purchaseOrderInfos)
			throws CalculationException {

		BigDecimal sumOfAllPriceAndQuantity = BigDecimal.ZERO;

		sumOfAllPriceAndQuantity = purchaseOrderInfos.stream().map(multiplyPriceAndQuantity).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		sumOfAllPriceAndQuantity.setScale(2, BigDecimal.ROUND_HALF_EVEN);

		Integer sumOfAllQuantities = purchaseOrderInfos.stream().map(PurchaseOrderInfo::getQuantity).reduce(0,
				Integer::sum);
		BigDecimal sumOfAllQuantitiesInBigDecimal = BigDecimal.valueOf(sumOfAllQuantities).setScale(2,
				BigDecimal.ROUND_HALF_EVEN);

		BigDecimal volumeWeightedOilPrice = sumOfAllPriceAndQuantity.divide(sumOfAllQuantitiesInBigDecimal,
				RoundingMode.HALF_UP);

		return volumeWeightedOilPrice;
	}

	private Function<PurchaseOrderInfo, BigDecimal> multiplyPriceAndQuantity = purchaseOrder -> new BigDecimal(
			purchaseOrder.getPrice() * purchaseOrder.getQuantity());

	/**
	 * Calculate the Inventory Index using the geometric mean of prices for all the
	 * types of oil.
	 * 
	 * @param purchaseOrderInfos
	 * @return BigDecimal
	 * @throws CalculationException
	 */
	public BigDecimal calculateGeometricMean(Collection<PurchaseOrderInfo> purchaseOrderInfos)
			throws CalculationException {

		if (Objects.isNull(purchaseOrderInfos) || purchaseOrderInfos.isEmpty())
			throw new CalculationException("Input collection should not be empty or null");

		List<Double> prices = purchaseOrderInfos.stream().map(PurchaseOrderInfo::getPrice).collect(Collectors.toList());

		int pricesCount = prices.size();

		Double sum = prices.stream().map(Math::log).reduce(Double.valueOf(0), Double::sum);

		sum = sum / pricesCount;

		double result = Math.exp(sum);

		return BigDecimal.valueOf(result);
	}

}
