package com.heno.fullback.model.entitiy;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

import java.io.Serializable;
import java.util.List;

/**
 * Sprint
 */
@Entity
public class Sprint implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sprint_id")
	private String sprintId;

	@Column(name = "sprint_name")
	private String sprintName;

	private List<Story> stories;


	public Integer calcStoryPoint() {
		return stories.stream()
				.mapToInt(s -> s.getStoryPoint())
				.sum();
	}

	public Integer calcAllWorkTime() {
		return stories.stream()
				.mapToInt(s -> s.calcWorkTime())
				.sum();
	}
}
