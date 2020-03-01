package com.techstack.gcmm.repository;

import com.techstack.gcmm.controller.api.PurchaseIndicator;
import com.techstack.gcmm.controller.api.PurchaseOrderInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;

/**
 * 
 * @author Karthikeyan N
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CollateralRepositoryTest {

	CollateralRepository repository = new CollateralRepositoryImpl();

	@Test
	public void testSave() {

		repository.save(new PurchaseOrderInfo(15, PurchaseIndicator.BUY, 15));
		assertEquals(1, repository.getTransactions().size());
	}

	@Test
	public void testGetTransactions() {

		Map<LocalDateTime, PurchaseOrderInfo> trans = repository.getTransactions();
		assertEquals(trans.isEmpty(), false);

	}

	@Test
	public void testFindLatestTransactionWithinLast30Minutes() {

		List<PurchaseOrderInfo> purchaseOrderInfos = repository.findLatestTransactionWithinLast30Minutes();
		assertEquals(purchaseOrderInfos.isEmpty(), false);

	}
}
