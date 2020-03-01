package com.techstack.gcmm.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.techstack.gcmm.controller.api.CalculationResultInfo;
import com.techstack.gcmm.controller.api.PurchaseOrderInfo;
import com.techstack.gcmm.formulary.CommonFormulary;
import com.techstack.gcmm.repository.CollateralRepositoryImpl;

/**
 * 
 * @author Karthikeyan N
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CollateralServiceImplTest {

	@Mock
	private CollateralRepositoryImpl collateralRepository;

	@Mock
	private CommonFormulary commonFormulary;

	@InjectMocks
	private CollateralService collateralService = new CollateralServiceImpl();

	@Test
	public void testProcessPurchaseOrder() throws Exception {

		PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo();

		when(commonFormulary.calculateRevenueYield(purchaseOrderInfo)).thenReturn(BigDecimal.ONE);

		when(commonFormulary.calculatePriceEarningRatio(BigDecimal.ONE, purchaseOrderInfo)).thenReturn(BigDecimal.ONE);

		CalculationResultInfo result = collateralService.processPurchaseOrder(purchaseOrderInfo);

		verify(collateralRepository, atLeastOnce()).save(purchaseOrderInfo);

		assertEquals(result.getRevenueYield(), BigDecimal.ONE);

	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testProcessPurchaseOrderShouldThrowAnException() throws Exception {

		PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo();

		when(commonFormulary.calculateRevenueYield(purchaseOrderInfo)).thenThrow(Exception.class);

		when(commonFormulary.calculatePriceEarningRatio(BigDecimal.ONE, purchaseOrderInfo)).thenThrow(Exception.class);

		collateralService.processPurchaseOrder(purchaseOrderInfo);

		verify(collateralRepository, atLeastOnce()).save(purchaseOrderInfo);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetVolumeWeightedOilPrice() throws Exception {

		when(collateralRepository.findLatestTransactionWithinLast30Minutes()).thenReturn(anyList());

		when(commonFormulary.calculateVolumeWeightedAverage(Collections.emptyList())).thenReturn(BigDecimal.ONE);

		CalculationResultInfo result = collateralService.getVolumeWeightedOilPrice();

		assertEquals(result.getVolumeWeightedOilPrice(), BigDecimal.ONE);

	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetVolumeWeightedOilPriceShouldReturnAnException() throws Exception {

		when(collateralRepository.findLatestTransactionWithinLast30Minutes()).thenThrow(Exception.class);

		when(commonFormulary.calculateVolumeWeightedAverage(Collections.emptyList())).thenThrow(Exception.class);

		collateralService.getVolumeWeightedOilPrice();

	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetInventoryIndex() throws Exception {

		when(commonFormulary.calculateGeometricMean(anyCollection())).thenReturn(BigDecimal.ONE);

		CalculationResultInfo result = collateralService.getInventoryIndex();

		verify(collateralRepository, atLeastOnce()).getTransactions();

		assertEquals(result.getInventoryIndex(), BigDecimal.ONE);

	}

	@SuppressWarnings("unchecked")
	@Test(expected = Exception.class)
	public void testGetInventoryIndexShouldThrowAnException() throws Exception {

		when(commonFormulary.calculateGeometricMean(anyCollection())).thenThrow(Exception.class);

		collateralService.getInventoryIndex();

		verify(collateralRepository, atLeastOnce()).getTransactions();

	}

}
