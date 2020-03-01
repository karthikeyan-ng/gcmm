package com.techstack.gcmm.repository;

import com.techstack.gcmm.controller.api.PurchaseOrderInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class stores each PurchaseOrderInfo in it's local in-memory data store.
 * Based on various business usecase, retrieval of data is possible
 * 
 * @author Karthikeyan N
 *
 */
public interface CollateralRepository {

	/**
	 * Save the given PurchaseOrderInfo in to in memory data store
	 * 
	 * @param purchaseOrderInfo
	 */
	void save(PurchaseOrderInfo purchaseOrderInfo);

	/**
	 * Get all the available Map Entries
	 * 
	 * @return Map of LocalDateTime as Key and PurchaseOrderInfo as Value
	 */
	Map<LocalDateTime, PurchaseOrderInfo> getTransactions();

	/**
	 * This method fetches last 30 minutes records from in-memory data store.
	 * 
	 * @return List of PurchaseOrderInfo
	 */
	List<PurchaseOrderInfo> findLatestTransactionWithinLast30Minutes();

}