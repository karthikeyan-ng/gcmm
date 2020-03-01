package com.techstack.gcmm.repository;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.techstack.gcmm.controller.api.PurchaseIndicator;
import com.techstack.gcmm.controller.api.PurchaseOrderInfo;

/**
 * 
 * @author Karthikeyan N
 *
 */
public class CollateralRepositoryTest {

	@InjectMocks
	CollateralRepository repository = new CollateralRepositoryImpl();

	@Mock
	private Map<LocalDateTime, PurchaseOrderInfo> transactions = new ConcurrentHashMap<>();

	@Test
	public void testSave() throws Exception {

		repository.save(new PurchaseOrderInfo(15, PurchaseIndicator.BUY, 15));
		assertEquals(1, repository.getTransactions().size());
	}

	@Test
	public void testGetTransactions() throws Exception {

		Map<LocalDateTime, PurchaseOrderInfo> trans = repository.getTransactions();
		assertEquals(trans.isEmpty(), false);

	}

	@Test
	public void testFindLatestTransactionWithinLast30Minutes() throws Exception {

		List<PurchaseOrderInfo> purchaseOrderInfos = repository.findLatestTransactionWithinLast30Minutes();
		assertEquals(purchaseOrderInfos.isEmpty(), false);

	}
}
