package com.techstack.gcmm.controller;

import com.techstack.gcmm.controller.api.CalculationResultInfo;
import com.techstack.gcmm.controller.api.PurchaseOrderInfo;
import com.techstack.gcmm.service.CollateralService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Global Collateral and Margin Management Controller will execute various Rest
 * end points based on various business use case.
 * 
 * @author Karthikeyan N
 *
 */
@RestController
@RequestMapping(path = CollateralController.PATH)
@Api(value = CollateralController.PATH)
public class CollateralController {

	public static final String PATH = "/gcmm";

	@Autowired
	private CollateralService collateralService;

	/**
	 * This operation performs the following business use case
	 * <li>Given any price as the input, calculate the Revenue yield,
	 * <li>Given any price as the input, calculate the Price-Earnings Ratio,
	 * 
	 * @param purchaseOrderInfo
	 * @return CalculationResultInfo
	 */
	@ApiOperation(value = "Process a given Purchase Order Information")
	@PostMapping(path = "/purchase-order")
	public ResponseEntity<CalculationResultInfo> processPurchaseOrder(
			@RequestBody PurchaseOrderInfo purchaseOrderInfo) {

		CalculationResultInfo resultInfo = collateralService.processPurchaseOrder(purchaseOrderInfo);
		return ResponseEntity.ok().body(resultInfo);
	}

	/**
	 * This operation calculates the Volume Weighted Oil Price
	 * 
	 * @return CalculationResultInfo
	 */
	@ApiOperation(value = "Calculate Volume Weighted Oil Price based on transaction in the past 30 minutes.")
	@GetMapping("/purchase-order/volume-weighted-oil-price")
	public ResponseEntity<CalculationResultInfo> getVolumeWeightedOilPrice() {

		CalculationResultInfo resultInfo = collateralService.getVolumeWeightedOilPrice();
		return ResponseEntity.ok().body(resultInfo);
	}

	/**
	 * This operation calculates the Inventory Index
	 * 
	 * @return CalculationResultInfo
	 */
	@ApiOperation(value = "Calculate the Inventory Index using the geometric mean of prices for all the types of oil.")
	@GetMapping("/inventory-index")
	public ResponseEntity<CalculationResultInfo> getInventoryIndex() {

		CalculationResultInfo resultInfo = collateralService.getInventoryIndex();
		return ResponseEntity.ok().body(resultInfo);
	}

}
