package com.techstack.gcmm.repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.techstack.gcmm.controller.api.PurchaseOrderInfo;
import com.techstack.gcmm.utils.DateUtility;

/**
 * 
 * This class stores each PurchaseOrderInfo in it's local in-memory data store.
 * Based on various business usecase, retrieval of data is possible
 * 
 * @author Karthikeyan N
 *
 */
@Repository
public class CollateralRepositoryImpl implements CollateralRepository {

	private static final long THIRTY_MINUTES = 30;

	/**
	 * This Map will hold all the PurchaseOrderInfo based on it's purchased Data and
	 * Time.
	 */
	private static final Map<LocalDateTime, PurchaseOrderInfo> transactions = new ConcurrentHashMap<>();

	/**
	 * Save the given PurchaseOrderInfo in to in memory data store
	 * 
	 * @param purchaseOrderInfo
	 */
	@Override
	public synchronized void save(PurchaseOrderInfo purchaseOrderInfo) {

		transactions.put(purchaseOrderInfo.getCurrentTime(), purchaseOrderInfo);
	}

	/**
	 * Get all the available Map Entries
	 * 
	 * @return Map of LocalDateTime as Key and PurchaseOrderInfo as Value
	 */
	@Override
	public Map<LocalDateTime, PurchaseOrderInfo> getTransactions() {
		return Collections.unmodifiableMap(transactions);
	}

	/**
	 * This method fetches last 30 minutes records from in-memory data store.
	 * 
	 * @return List of PurchaseOrderInfo
	 */
	@Override
	public List<PurchaseOrderInfo> findLatestTransactionWithinLast30Minutes() {

		LocalDateTime currentTime = LocalDateTime.now();

		return getTransactions().entrySet().stream()
				.filter(e -> DateUtility.isFallIn30MinutesRange(e.getKey(), currentTime, THIRTY_MINUTES))
				.map(e -> e.getValue()).collect(Collectors.toList());

	}
}
