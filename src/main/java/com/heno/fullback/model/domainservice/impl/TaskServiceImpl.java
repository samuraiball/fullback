package com.heno.fullback.model.domainservice.impl;

import com.heno.fullback.model.valueobject.Task;
import com.heno.fullback.model.repository.TaskDao;
import com.heno.fullback.model.domainservice.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

	private final TaskDao taskDao;

	public TaskServiceImpl(TaskDao taskDao) {
		this.taskDao = taskDao;
	}


	@Override
	public List<Task> getAllTasks() {
		return taskDao.selectAll();
	}

	@Override
	public Task getTask(String taskId) {
		return taskDao.selectById(taskId);
	}

	@Override
	public Task create(Task task) {
		taskDao.insert(task);
		return task;
	}
}
