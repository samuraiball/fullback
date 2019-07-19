package com.heno.fullback.model.domainservice;


import com.heno.fullback.model.valueobject.Task;

import java.util.List;

/**
 * Task Domain Service InterFace
 */
public interface TaskService {

	public List<Task> getAllTasks();
	public Task getTask(String taskId);
	public Task create(Task task);

}
