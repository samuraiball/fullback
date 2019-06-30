package com.heno.fullback.controller;

import com.heno.fullback.model.common.Status;
import com.heno.fullback.model.dto.TaskResource;
import com.heno.fullback.model.entitiy.Task;
import com.heno.fullback.model.entitiy.builder.TaskBuilder;
import com.heno.fullback.model.service.TaskService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TaskController {

	TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}


	@GetMapping("/tasks/{taskId}")
	public Task getAllTask(
			@PathVariable String taskId
	) {
		return taskService.getTask(taskId);
	}


	@GetMapping("/task/{taskId}")
	public Task getTask(
			@PathVariable String taskId
	) {
		return null;
	}

	@PostMapping("/task")
	public ResponseEntity<Task> postTask(
			@RequestBody @Validated TaskResource taskResource,
			UriComponentsBuilder uriBuilder
	) {
		Task createdTask = taskService.create(
				new TaskBuilder()
						.withTaskId(UUID.randomUUID().toString())
						.withTaskName(taskResource.getTaskName())
						.withDescription(taskResource.getDescription())
						.withSprintId(taskResource.getSprintId())
						.withInChange(taskResource.getInChange())
						.withTaskStatus(Status.TODO)
						.createTask());

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriBuilder.path("/member/{memberId}")
				.buildAndExpand(createdTask.getTaskId()).toUri());
		return new ResponseEntity(createdTask, headers, HttpStatus.CREATED);
	}

	@PutMapping("/task")
	public Task putTask() {
		return null;
	}

	@DeleteMapping("/task/{taskId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTask(
			@PathVariable String taskId
	) {
	}
}
