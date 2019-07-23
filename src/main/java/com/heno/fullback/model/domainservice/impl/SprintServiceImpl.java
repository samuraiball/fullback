package com.heno.fullback.model.domainservice.impl;

import com.heno.fullback.model.domainservice.SprintService;
import com.heno.fullback.repository.SprintDao;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Sprint Domain Service Impl
 */
@Service
public class SprintServiceImpl implements SprintService {


	private SprintDao sprintDao;

	public SprintServiceImpl(SprintDao sprintDao){
		this.sprintDao = sprintDao;
	}

	@Override
	public BigDecimal calcNeedsTimePerDay(String sprintId) {
		sprintDao.findById(sprintId);
		return null;
	}
}
