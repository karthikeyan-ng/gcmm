package com.techstack.gcmm.formulary;

import com.techstack.gcmm.controller.api.PurchaseIndicator;
import com.techstack.gcmm.controller.api.PurchaseOrderInfo;
import com.techstack.gcmm.exception.CalculationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @author Karthikeyan N
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CommonFormularyTest {

	@InjectMocks
	private CommonFormulary commonFormulary;

	@Test
	public void testCalculateRevenueYield() {

		BigDecimal givenRevenueYield = new BigDecimal(5.466666666666667).setScale(2, BigDecimal.ROUND_HALF_EVEN);

		BigDecimal revenueYield = commonFormulary
				.calculateRevenueYield(new PurchaseOrderInfo(5, PurchaseIndicator.BUY, 15d));
		revenueYield = revenueYield.setScale(2, BigDecimal.ROUND_HALF_EVEN);

		assertEquals(givenRevenueYield, revenueYield);

	}

	@Test(expected = CalculationException.class)
	public void testCalculateRevenueYield_WhenPriceIsZero() {

		commonFormulary.calculateRevenueYield(new PurchaseOrderInfo(5, PurchaseIndicator.BUY, 0d));

	}

	@Test
	public void testCalculateEarningRatio() {

		BigDecimal revenue = new BigDecimal(5.4666666666666665);

		BigDecimal expected = BigDecimal.valueOf(2.7);

		BigDecimal actual = commonFormulary.calculatePriceEarningRatio(revenue,
				new PurchaseOrderInfo(5, PurchaseIndicator.BUY, 15d));

		assertEquals(expected, actual);
	}

	@Test(expected = CalculationException.class)
	public void testCalculateEarningRatio_WhenRevenueIsZero() {

		BigDecimal revenue = BigDecimal.ZERO;

		commonFormulary.calculatePriceEarningRatio(revenue, new PurchaseOrderInfo(5, PurchaseIndicator.BUY, 15d));

	}

	@Test
	public void testCalculateVolumeWeightedAverage() {

		BigDecimal expectedValue = BigDecimal.valueOf(23);

		List<PurchaseOrderInfo> purchaseOrderInfos = new ArrayList<>();

		purchaseOrderInfos.add(new PurchaseOrderInfo(5, PurchaseIndicator.BUY, 15d));
		purchaseOrderInfos.add(new PurchaseOrderInfo(6, PurchaseIndicator.BUY, 20d));
		purchaseOrderInfos.add(new PurchaseOrderInfo(7, PurchaseIndicator.BUY, 25d));
		purchaseOrderInfos.add(new PurchaseOrderInfo(8, PurchaseIndicator.BUY, 30d));

		BigDecimal volumeWeightedOilPrice = commonFormulary.calculateVolumeWeightedAverage(purchaseOrderInfos);

		assertEquals(expectedValue, volumeWeightedOilPrice);

	}

	@Test
	public void testCalculateGeometricMean() {

		BigDecimal expectedValue = BigDecimal.valueOf(21.779385873464317);

		List<PurchaseOrderInfo> purchaseOrderInfos = new ArrayList<>();

		purchaseOrderInfos.add(new PurchaseOrderInfo(5, PurchaseIndicator.BUY, 15d));
		purchaseOrderInfos.add(new PurchaseOrderInfo(6, PurchaseIndicator.BUY, 20d));
		purchaseOrderInfos.add(new PurchaseOrderInfo(7, PurchaseIndicator.BUY, 25d));
		purchaseOrderInfos.add(new PurchaseOrderInfo(8, PurchaseIndicator.BUY, 30d));

		BigDecimal actualValue = commonFormulary.calculateGeometricMean(purchaseOrderInfos);

		assertEquals(expectedValue, actualValue);
	}

	@Test(expected = CalculationException.class)
	public void testCalculateGeometricMean_withEmptyCollection() {
		List<PurchaseOrderInfo> purchaseOrderInfos = Collections.emptyList();
		commonFormulary.calculateGeometricMean(purchaseOrderInfos);
	}

	@Test(expected = CalculationException.class)
	public void testCalculateGeometricMean_withNullCollection() {
		List<PurchaseOrderInfo> purchaseOrderInfos = null;
		commonFormulary.calculateGeometricMean(purchaseOrderInfos);
	}

}
