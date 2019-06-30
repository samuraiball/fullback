package com.heno.fullback.model.service;


import com.heno.fullback.model.entitiy.Task;

public interface TaskService {

	public Task getTask(String taskId);
	public Task create(Task task);

}
