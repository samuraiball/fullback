package com.heno.fullback.model.entitiy;

import com.heno.fullback.model.common.Status;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class Task implements Serializable {

	public Task(
			String taskId, String sprintId,
			String taskName, String description,
			String inChange, Status taskStatus,
			Timestamp createdDate, Timestamp update_time,
			boolean deleted_flag
	) {
		this.taskId = taskId;
		this.sprintId = sprintId;
		this.taskName = taskName;
		this.description = description;
		this.inChange = inChange;
		this.taskStatus = taskStatus;
		this.createdDate = createdDate;
		this.update_time = update_time;
		this.deleted_flag = deleted_flag;
	}

	@Id
	@Column(name = "task_id")
	private String taskId;

	@Column(name = "sprint_id")
	private String sprintId;

	@Column(name = "task_name")
	private String taskName;

	@Column(name = "description")
	private String description;


	@Column(name = "member_id")
	private String inChange;

	@Column(name = "task_status")
	private Status taskStatus;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "update_time")
	private Timestamp update_time;

	@Column(name = "deleted_flag")
	private boolean deleted_flag;

	public String getInChange() {
		return inChange;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public boolean isDeleted_flag() {
		return deleted_flag;
	}

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
