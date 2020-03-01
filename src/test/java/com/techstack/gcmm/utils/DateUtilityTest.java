package com.techstack.gcmm.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * 
 * @author Karthikeyan N
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DateUtilityTest {

	@Test
	public void testTheGiveDatesAreInRangeForTheSpecifiedDays_TrueCase() throws Exception {

		LocalDateTime now = LocalDateTime.now();

		LocalDateTime nowMinue25Mins = now.minusMinutes(25);

		boolean result = DateUtility.isFallIn30MinutesRange(nowMinue25Mins, now, 30);

		assertTrue(result);
	}

	@Test
	public void testTheGiveDatesAreInRangeForTheSpecifiedDays_FalseCase() throws Exception {

		LocalDateTime now = LocalDateTime.now();

		LocalDateTime nowMinue35Mins = now.minusMinutes(35);

		boolean result = DateUtility.isFallIn30MinutesRange(nowMinue35Mins, now, 30);

		assertFalse(result);
	}

}
