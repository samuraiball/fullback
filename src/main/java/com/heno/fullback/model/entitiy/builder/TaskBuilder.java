package com.heno.fullback.model.entitiy.builder;

import com.heno.fullback.model.states.Status;
import com.heno.fullback.model.valueobject.Task;

import java.sql.Timestamp;

public class TaskBuilder {
	private String taskId;
	private String sprintId;
	private String taskName;
	private String description;
	private String inChange;
	private Status taskStatus;
	private Timestamp createdDate;
	private Timestamp update_time;

	public TaskBuilder withTaskId(String taskId) {
		this.taskId = taskId;
		return this;
	}

	public TaskBuilder withSprintId(String sprintId) {
		this.sprintId = sprintId;
		return this;
	}

	public TaskBuilder withTaskName(String taskName) {
		this.taskName = taskName;
		return this;
	}

	public TaskBuilder withDescription(String description) {
		this.description = description;
		return this;
	}

	public TaskBuilder withInChange(String inChange) {
		this.inChange = inChange;
		return this;
	}

	public TaskBuilder withTaskStatus(Status taskStatus) {
		this.taskStatus = taskStatus;
		return this;
	}

	public TaskBuilder withCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
		return this;
	}

	public TaskBuilder withUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
		return this;
	}

	public Task createTask() {
		return new com.heno.fullback.model.entitiy.TaskBuilder().withTaskId(taskId).withSprintId(sprintId).withTaskName(taskName).withDescription(description).withInChange(inChange).withTaskStatus(taskStatus).withCreatedDate(createdDate).withUpdate_time(update_time).createTask();
	}
}