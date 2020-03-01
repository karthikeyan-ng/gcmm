package com.techstack.gcmm.controller.api;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * 
 * @author Karthikeyan N
 *
 */
public class CalculationResultInfoTest {

	@Test
	public void testNoArgsContstructorForJson() {

		CalculationResultInfo calculationResultInfo = new CalculationResultInfo();

		assertNotNull(calculationResultInfo);

	}

	@Test
	public void testAllArgsContstructorForJson() {

		BigDecimal revenueYield = new BigDecimal(10.0);
		BigDecimal priceEarningRatio = new BigDecimal(15.76);
		BigDecimal volumeWeightedOilPrice = new BigDecimal(278.34);
		BigDecimal inventoryIndex = new BigDecimal(55);

		CalculationResultInfo calculationResultInfo = new CalculationResultInfo(revenueYield, priceEarningRatio,
				volumeWeightedOilPrice, inventoryIndex);

		assertNotNull(calculationResultInfo);

	}

	@Test
	public void testBuilderFunction() {

		BigDecimal revenueYield = new BigDecimal(10.0);
		BigDecimal priceEarningRatio = new BigDecimal(15.76);
		BigDecimal volumeWeightedOilPrice = new BigDecimal(278.34);
		BigDecimal inventoryIndex = new BigDecimal(55);

		CalculationResultInfo calculationResultInfo = CalculationResultInfo.builder().revenueYield(revenueYield)
				.priceEarningRatio(priceEarningRatio).volumeWeightedOilPrice(volumeWeightedOilPrice)
				.inventoryIndex(inventoryIndex).build();

		assertNotNull(calculationResultInfo);

	}

	@Test
	public void testGetterAndSetterFunction() {

		BigDecimal revenueYield = new BigDecimal(10.0);
		BigDecimal priceEarningRatio = new BigDecimal(15.76);
		BigDecimal volumeWeightedOilPrice = new BigDecimal(278.34);
		BigDecimal inventoryIndex = new BigDecimal(55);

		CalculationResultInfo calculationResultInfo = new CalculationResultInfo();
		calculationResultInfo.setInventoryIndex(inventoryIndex);
		calculationResultInfo.setPriceEarningRatio(priceEarningRatio);
		calculationResultInfo.setRevenueYield(revenueYield);
		calculationResultInfo.setVolumeWeightedOilPrice(volumeWeightedOilPrice);

		assertNotNull(calculationResultInfo);
		assertNotNull(calculationResultInfo.getInventoryIndex());
		assertNotNull(calculationResultInfo.getPriceEarningRatio());
		assertNotNull(calculationResultInfo.getRevenueYield());
		assertNotNull(calculationResultInfo.getVolumeWeightedOilPrice());
		assertNotNull(calculationResultInfo.toString());
		assertFalse(calculationResultInfo.equals(new CalculationResultInfo()));
		assertNotNull(calculationResultInfo.hashCode());

	}

}
