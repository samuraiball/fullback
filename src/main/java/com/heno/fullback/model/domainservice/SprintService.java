package com.heno.fullback.model.domainservice;

import java.math.BigDecimal;

public interface SprintService {

	BigDecimal calcNeedsTimePerDay(String sprintId);
}
