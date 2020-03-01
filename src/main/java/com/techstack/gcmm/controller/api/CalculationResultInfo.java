package com.techstack.gcmm.controller.api;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class holds response information for various business calculations
 * 
 * @author Karthikeyan N
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class CalculationResultInfo {

	private BigDecimal revenueYield;

	private BigDecimal priceEarningRatio;

	private BigDecimal volumeWeightedOilPrice;

	private BigDecimal inventoryIndex;

}
