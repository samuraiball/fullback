package com.heno.fullback.model.entitiy;

import com.heno.fullback.model.common.Status;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Task implements Serializable {

	@Id
	@Column(name = "task_id")
	private String taskId;

	@Column(name = "sprint_id")
	private String sprintId;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "description")
	private String description;

	@Column(name = "update_time")
	private Timestamp update_time;

	@Column(name = "task_status")
	private Status taskStatus;

	public String getTaskId() {
		return taskId;
	}

	public String getSprintId() {
		return sprintId;
	}

	public String getTaskName() {
		return taskName;
	}

	public String getDescription() {
		return description;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public Status getTaskStatus() {
		return taskStatus;
	}
}
