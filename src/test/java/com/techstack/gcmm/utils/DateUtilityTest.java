package com.techstack.gcmm.utils;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * 
 * @author Karthikeyan N
 *
 */
public class DateUtilityTest {

	@Test
	public void testTheGiveDatesAreInRangeForTheSpecifiedDays_TrueCase() {

		LocalDateTime now = LocalDateTime.now();

		LocalDateTime nowMinue25Mins = now.minusMinutes(25);

		boolean result = DateUtility.isDatesFallInGivenMinutesRange(nowMinue25Mins, now, 30);

		assertTrue(result);
	}

	@Test
	public void testTheGiveDatesAreInRangeForTheSpecifiedDays_FalseCase() {

		LocalDateTime now = LocalDateTime.now();

		LocalDateTime nowMinue35Mins = now.minusMinutes(35);

		boolean result = DateUtility.isDatesFallInGivenMinutesRange(nowMinue35Mins, now, 30);

		assertFalse(result);
	}

}
