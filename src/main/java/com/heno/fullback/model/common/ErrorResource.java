package com.heno.fullback.model.common;

import java.time.LocalDateTime;

public class ErrorResource {


	public ErrorResource(LocalDateTime time, String message) {
		this.time = time;
		this.message = message;
	}

	private final String message;
	private final LocalDateTime time;


	public String getMessage() {
		return message;
	}

	public LocalDateTime getTime() {
		return time;
	}
}
