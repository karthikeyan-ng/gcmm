package com.techstack.gcmm.service;

import com.techstack.gcmm.controller.api.CalculationResultInfo;
import com.techstack.gcmm.controller.api.PurchaseOrderInfo;

/**
 * This class holds certain business operation based on it's use case
 * 
 * @author Karthikeyan N
 *
 */
public interface CollateralService {

	/**
	 * This method is responsible for processing the given PurchaseOrderInfo
	 * 
	 * @param purchaseOrderInfo
	 * @return CalculationResultInfo
	 */
	CalculationResultInfo processPurchaseOrder(PurchaseOrderInfo purchaseOrderInfo);

	/**
	 * This method is responsible to get the Volume Weighted Oil Price
	 * 
	 * @return CalculationResultInfo
	 */
	CalculationResultInfo getVolumeWeightedOilPrice();

	/**
	 * This method is responsible to get the Inventory Index
	 * 
	 * @return CalculationResultInfo
	 */
	CalculationResultInfo getInventoryIndex();

}