package com.heno.fullback.model.domainservice;

import java.math.BigDecimal;

/**
 * Sprint Domain Service InterFace.
 */
public interface SprintService {
	BigDecimal calcNeedsTimePerDay(String sprintId);
}
