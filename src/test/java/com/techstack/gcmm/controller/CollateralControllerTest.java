package com.techstack.gcmm.controller;

import com.techstack.gcmm.controller.api.CalculationResultInfo;
import com.techstack.gcmm.controller.api.PurchaseOrderInfo;
import com.techstack.gcmm.service.CollateralService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
	private CollateralController controller;

	@Test
	public void testProcessPurchaseOrder() {

		PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo();
		CalculationResultInfo calculationResultInfo = getCalculationInfo();

		when(collateralService.processPurchaseOrder(purchaseOrderInfo)).thenReturn(calculationResultInfo);

		ResponseEntity<CalculationResultInfo> result = controller.processPurchaseOrder(purchaseOrderInfo);

		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals(result.getBody().getRevenueYield(), BigDecimal.valueOf(1));
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testProcessPurchaseOrderShouldThrowAnException() {

		PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo();

		when(collateralService.processPurchaseOrder(purchaseOrderInfo)).thenThrow(Exception.class);

		controller.processPurchaseOrder(purchaseOrderInfo);

	}

	@Test
	public void testGetVolumeWeightedOilPrice() {

		CalculationResultInfo calculationResultInfo = getCalculationInfo();

		when(collateralService.getVolumeWeightedOilPrice()).thenReturn(calculationResultInfo);

		ResponseEntity<CalculationResultInfo> result = controller.getVolumeWeightedOilPrice();

		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals(result.getBody().getVolumeWeightedOilPrice(), BigDecimal.valueOf(1));
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetVolumeWeightedOilPriceShouldThorwAnException() {

		when(collateralService.getVolumeWeightedOilPrice()).thenThrow(Exception.class);

		controller.getVolumeWeightedOilPrice();
	}

	@Test
	public void testGetInventoryIndex() {

		CalculationResultInfo calculationResultInfo = getCalculationInfo();

		when(collateralService.getInventoryIndex()).thenReturn(calculationResultInfo);

		ResponseEntity<CalculationResultInfo> result = controller.getInventoryIndex();

		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals(result.getBody().getInventoryIndex(), BigDecimal.valueOf(1));
	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetInventoryIndexShouldReturnAnException() {

		when(collateralService.getInventoryIndex()).thenThrow(Exception.class);

		controller.getInventoryIndex();
	}

	private CalculationResultInfo getCalculationInfo() {
		return CalculationResultInfo.builder().revenueYield(BigDecimal.ONE).priceEarningRatio(BigDecimal.ONE)
				.volumeWeightedOilPrice(BigDecimal.ONE).inventoryIndex(BigDecimal.ONE).build();
	}

}
