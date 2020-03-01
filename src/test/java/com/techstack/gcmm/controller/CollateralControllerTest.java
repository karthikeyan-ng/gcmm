package com.techstack.gcmm.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
//import static org.mockito.Mockito;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.techstack.gcmm.controller.api.CalculationResultInfo;
import com.techstack.gcmm.controller.api.PurchaseOrderInfo;
import com.techstack.gcmm.service.CollateralService;

/**
 * 
 * @author Karthikeyan N
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CollateralControllerTest {

	@Mock
	private CollateralService collateralService;

	@InjectMocks
	private CollateralController controller; // = new CollateralController();

	@Test
	public void testProcessPurchaseOrder() throws Exception {

		PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo();
		CalculationResultInfo calculationResultInfo = getCalculationInfo();

		when(collateralService.processPurchaseOrder(purchaseOrderInfo)).thenReturn(calculationResultInfo);

		ResponseEntity<CalculationResultInfo> result = controller.processPurchaseOrder(purchaseOrderInfo);

		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals(result.getBody().getRevenueYield(), BigDecimal.valueOf(1));
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testProcessPurchaseOrderShouldThrowAnException() throws Exception {

		PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo();

		when(collateralService.processPurchaseOrder(purchaseOrderInfo)).thenThrow(Exception.class);

		controller.processPurchaseOrder(purchaseOrderInfo);

	}

	@Test
	public void testGetVolumeWeightedOilPrice() throws Exception {

		CalculationResultInfo calculationResultInfo = getCalculationInfo();

		when(collateralService.getVolumeWeightedOilPrice()).thenReturn(calculationResultInfo);

		ResponseEntity<CalculationResultInfo> result = controller.getVolumeWeightedOilPrice();

		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals(result.getBody().getVolumeWeightedOilPrice(), BigDecimal.valueOf(1));
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetVolumeWeightedOilPriceShouldThorwAnException() throws Exception {

		when(collateralService.getVolumeWeightedOilPrice()).thenThrow(Exception.class);

		controller.getVolumeWeightedOilPrice();
	}

	@Test
	public void testGetInventoryIndex() throws Exception {

		CalculationResultInfo calculationResultInfo = getCalculationInfo();

		when(collateralService.getInventoryIndex()).thenReturn(calculationResultInfo);

		ResponseEntity<CalculationResultInfo> result = controller.getInventoryIndex();

		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals(result.getBody().getInventoryIndex(), BigDecimal.valueOf(1));
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetInventoryIndexShouldReturnAnException() throws Exception {

		when(collateralService.getInventoryIndex()).thenThrow(Exception.class);

		controller.getInventoryIndex();
	}

	private CalculationResultInfo getCalculationInfo() {
		return CalculationResultInfo.builder().revenueYield(BigDecimal.ONE).priceEarningRatio(BigDecimal.ONE)
				.volumeWeightedOilPrice(BigDecimal.ONE).inventoryIndex(BigDecimal.ONE).build();
	}

}
