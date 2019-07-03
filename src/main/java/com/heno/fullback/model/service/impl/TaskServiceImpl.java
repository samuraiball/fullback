package com.heno.fullback.model.service.impl;

import com.heno.fullback.model.entitiy.Task;
import com.heno.fullback.model.repository.TaskDao;
import com.heno.fullback.model.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

	private final TaskDao taskDao;

	public TaskServiceImpl(TaskDao taskDao) {
		this.taskDao = taskDao;
	}


	@Override
	public List<Task> getAllTask(Task task) {
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
