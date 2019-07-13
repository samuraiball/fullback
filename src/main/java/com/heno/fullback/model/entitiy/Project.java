package com.heno.fullback.model.entitiy;

import com.heno.fullback.model.valueobject.Member;
import org.seasar.doma.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;

	private String projectId;

	private String projectName;

	private Integer sprintPeriodDays;

	private List<Sprint> sprints;

	private Team team;

	public double calcNeedsSprints() {
		double sp = sprints.stream()
				.mapToDouble(spr -> spr.calcStoryPoint())
				.sum();
		return sp / team.getVelocity();
	}
}
