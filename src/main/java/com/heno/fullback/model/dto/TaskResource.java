package com.heno.fullback.model.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TaskResource implements Serializable {

	public TaskResource() {
	}

	public TaskResource(
			String sprintId, String taskName,
			String description, String inChange
	) {
		this.taskName = taskName;
		this.sprintId = sprintId;
		this.description = description;
		this.inChange = inChange;
	}

	private String taskName;

	private String sprintId;

	private String description;

	private String inChange;

	public String getSprintId() {
		return sprintId;
	}

	public String getTaskName() {
		return taskName;
	}

	public String getDescription() {
		return description;
	}

	public String getInChange() {
		return inChange;
	}


}
