package com.techstack.gcmm.controller.api;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 
 * This class holds information about the Purchase Order
 * 
 * @author Karthikeyan N
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PurchaseOrderInfo {

	private int quantity;

	private PurchaseIndicator purchaseIndicator;

	private double price;

	@JsonIgnore
	private LocalDateTime currentTime = LocalDateTime.now();

	public PurchaseOrderInfo(@JsonProperty(required = true) int quantity,
			@JsonProperty(required = true) PurchaseIndicator purchaseIndicator,
			@JsonProperty(required = true) double price) {
		this.quantity = quantity;
		this.purchaseIndicator = purchaseIndicator;
		this.price = price;
		this.currentTime = LocalDateTime.now();
	}

}
