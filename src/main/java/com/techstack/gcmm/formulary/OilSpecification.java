package com.techstack.gcmm.formulary;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class holds information about Oil IDs and it's specification. This
 * specification data will be used during the calculation logic.
 * 
 * @author Karthikeyan N
 *
 */
@AllArgsConstructor
@Getter
public enum OilSpecification {

	AAC(OilSpecification.OIL_TYPE_STANDARD, 1, 0, 42),

	REW(OilSpecification.OIL_TYPE_STANDARD, 7, 0, 47),

	BWO(OilSpecification.OIL_TYPE_STANDARD, 17, 0, 61),

	TIM(OilSpecification.OIL_TYPE_PREMIUM, 5, 7, 111),

	QFC(OilSpecification.OIL_TYPE_STANDARD, 22, 0, 123);

	public static final String OIL_TYPE_STANDARD = "Standard";
	public static final String OIL_TYPE_PREMIUM = "Premium";

	private final String type;

	private final int fixedRevenue;

	private final double variableRevenue;

	private final double oildBarrelValue;

}
