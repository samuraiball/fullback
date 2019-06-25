package com.heno.fullback.controller;

import com.heno.fullback.model.entitiy.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TaskController {

	@GetMapping("/tasks")
	public Task getAllTask() {
		return null;
	}


	@GetMapping("/task/{taskId}")
	public Task getTask(
			@PathVariable String taskId
	) {
		return null;
	}

	@PostMapping("/task")
	public ResponseEntity<Task> postTask() {
		return null;
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
