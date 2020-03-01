package com.techstack.gcmm.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * This is an Utility class for Date related process
 * 
 * @author Karthikeyan N
 *
 */
public final class DateUtility {

	private DateUtility() {
	}

	/**
	 * This Utility method takes two LocalDateTime as it's input followed by minutes
	 * to perform the given two dates are is in 30 minutes range
	 * 
	 * 
	 * @param from              LocalDateTime
	 * @param to                LocalDateTime
	 * @param durationInMinutes
	 * @return boolean
	 */
	public static boolean isFallIn30MinutesRange(LocalDateTime from, LocalDateTime to, final long durationInMinutes) {

		long diff = Math.abs(ChronoUnit.MINUTES.between(to, from));
		return diff <= durationInMinutes ? true : false;

	}

}
