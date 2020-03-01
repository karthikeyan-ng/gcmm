package com.techstack.gcmm.controller.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;

/**
 * 
 * @author Karthikeyan N
 *
 */
public class PurchaseOrderInfoTest {

	@Test
	public void testNoArgsContstructorForJson() {

		PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo();

		assertNotNull(purchaseOrderInfo);

	}

	@Test
	public void testAllArgsContstructorForJson() {

		int quantity = 15;
		PurchaseIndicator purchaseIndicator = PurchaseIndicator.SELL;
		double price = 15;
		LocalDateTime now = LocalDateTime.now();

		PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo(quantity, purchaseIndicator, price, now);
		;

		assertNotNull(purchaseOrderInfo);

	}

	@Test
	public void testGetterAndSetterFunction() {
		int quantity = 15;
		PurchaseIndicator purchaseIndicator = PurchaseIndicator.BUY;
		double price = 15;
		LocalDateTime now = LocalDateTime.now();

		PurchaseOrderInfo purchaseOrderInfo = new PurchaseOrderInfo();
		purchaseOrderInfo.setQuantity(quantity);
		purchaseOrderInfo.setPurchaseIndicator(purchaseIndicator);
		purchaseOrderInfo.setPrice(price);
		purchaseOrderInfo.setCurrentTime(now);

		assertNotNull(purchaseOrderInfo.getQuantity());
		assertNotNull(purchaseOrderInfo.getPurchaseIndicator());
		assertNotNull(purchaseOrderInfo.getPrice());
		assertNotNull(purchaseOrderInfo.getCurrentTime());
		assertNotNull(purchaseOrderInfo.toString());
		assertFalse(purchaseOrderInfo.equals(new PurchaseOrderInfo()));
		assertNotNull(purchaseOrderInfo.hashCode());

	}

}
