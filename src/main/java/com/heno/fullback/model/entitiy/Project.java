package com.heno.fullback.model.entitiy;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

	private String projectId;

	private String projectName;

	private Integer sprintPeriodDays;

	private Date startDate;

	private Date periodDate;

	private List<Sprint> sprints;

	private Team team;

	public double calcNeedsSprints() {
		double sp = sprints.stream()
				.mapToDouble(spr -> spr.calcStoryPoint())
				.sum();
		return sp / team.getVelocity();
	}
}
