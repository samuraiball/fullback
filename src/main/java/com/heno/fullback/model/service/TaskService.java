package com.heno.fullback.model.service;


import com.heno.fullback.model.entitiy.Task;

import java.util.List;

public interface TaskService {

	public List<Task> getAllTask(Task task);
	public Task getTask(String taskId);
	public Task create(Task task);

}
