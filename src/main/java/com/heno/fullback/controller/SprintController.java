package com.heno.fullback.controller;

import com.heno.fullback.model.common.Status;
import com.heno.fullback.model.dto.TaskResource;
import com.heno.fullback.model.valueobject.Task;
import com.heno.fullback.model.entitiy.builder.TaskBuilder;
import com.heno.fullback.model.domainservice.TaskService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class SprintController {

	TaskService taskService;

	public SprintController(TaskService taskService) {
		this.taskService = taskService;
	}

	/**
	 * Handler to get  task's Information that Member is specified by ID.
	 *
	 * @return List of tasks
	 */
	@GetMapping("/tasks/{sprintId}")
	public List<Task> getAllTasks(
			@PathVariable String sprintId
	) {
		return taskService.getAllTasks();
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
