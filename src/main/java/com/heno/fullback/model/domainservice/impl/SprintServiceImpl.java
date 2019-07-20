package com.heno.fullback.model.domainservice.impl;

import com.heno.fullback.model.domainservice.SprintService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Sprint Domain Service Impl
 */
@Service
public class SprintServiceImpl implements SprintService {
	@Override
	public BigDecimal calcNeedsTimePerDay(String sprintId) {
		return null;
	}
}
