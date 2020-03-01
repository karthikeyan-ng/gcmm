package com.techstack.gcmm.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techstack.gcmm.controller.api.CalculationResultInfo;
import com.techstack.gcmm.controller.api.PurchaseOrderInfo;
import com.techstack.gcmm.formulary.CommonFormulary;
import com.techstack.gcmm.repository.CollateralRepository;

/**
 * This class is an implementation of {@link CollateralService}
 * 
 * @author Karthikeyan N
 *
 */
@Service
public class CollateralServiceImpl implements CollateralService {

	@Autowired
	private CollateralRepository collateralRepository;

	@Autowired
	private CommonFormulary commonFormulary;

	@Override
	public CalculationResultInfo processPurchaseOrder(final PurchaseOrderInfo purchaseOrderInfo) {

		BigDecimal revenueYield = commonFormulary.calculateRevenueYield(purchaseOrderInfo);

		BigDecimal earningRatio = commonFormulary.calculatePriceEarningRatio(revenueYield, purchaseOrderInfo);

		collateralRepository.save(purchaseOrderInfo);

		CalculationResultInfo resultInfo = CalculationResultInfo.builder().revenueYield(revenueYield)
				.priceEarningRatio(earningRatio).build();
		return resultInfo;
	}

	@Override
	public CalculationResultInfo getVolumeWeightedOilPrice() {

		List<PurchaseOrderInfo> purchaseOrderInfos = collateralRepository.findLatestTransactionWithinLast30Minutes();

		BigDecimal volumeWeightedOilPrice = commonFormulary.calculateVolumeWeightedAverage(purchaseOrderInfos);

		CalculationResultInfo resultInfo = CalculationResultInfo.builder()
				.volumeWeightedOilPrice(volumeWeightedOilPrice).build();
		return resultInfo;
	}

	@Override
	public CalculationResultInfo getInventoryIndex() {
		Collection<PurchaseOrderInfo> purchaseOrderInfos = collateralRepository.getTransactions().values();

		BigDecimal inventoryIndex = commonFormulary.calculateGeometricMean(purchaseOrderInfos);

		CalculationResultInfo resultInfo = CalculationResultInfo.builder().inventoryIndex(inventoryIndex).build();
		return resultInfo;
	}

}
