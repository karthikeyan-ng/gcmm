package com.techstack.gcmm.repository;

import com.techstack.gcmm.controller.api.PurchaseOrderInfo;
import com.techstack.gcmm.utils.DateUtility;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
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

	@Override
	public synchronized void save(PurchaseOrderInfo purchaseOrderInfo) {

		transactions.put(purchaseOrderInfo.getCurrentTime(), purchaseOrderInfo);
	}

	@Override
	public Map<LocalDateTime, PurchaseOrderInfo> getTransactions() {
		return Collections.unmodifiableMap(transactions);
	}

	@Override
	public List<PurchaseOrderInfo> findLatestTransactionWithinLast30Minutes() {

		LocalDateTime currentTime = LocalDateTime.now();

		return getTransactions().entrySet().stream()
				.filter(e -> DateUtility.isDatesFallInGivenMinutesRange(e.getKey(), currentTime, THIRTY_MINUTES))
				.map(e -> e.getValue()).collect(Collectors.toList());

	}
}
