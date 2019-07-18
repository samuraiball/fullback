package com.heno.fullback.model.entitiy;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Project
 */
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * ID
	 */
	private String projectId;

	/**
	 * Project Name
	 */
	private String projectName;

	/**
	 * Sprint Period Days
	 * days for one sprint
	 */
	private Integer sprintPeriodDays;

	/**
	 * Start Date
	 * when a project starts
	 */
	private Date startDate;

	/**
	 * Start Date
	 * when a project ends
	 */
	private Date periodDate;

	/**
	 * Sprint
	 */
	private List<Sprint> sprints;

	/**
	 * Team
	 * a team that
	 */
	private Team team;

	/**
	 * Calculate how many sprint dose it need
	 * @return needs sprint number
	 */
	public double calcNeedsSprints() {
		double sp = sprints.stream()
				.mapToDouble(spr -> spr.calcStoryPoint())
				.sum();
		return sp / team.getVelocity();
	}
}
