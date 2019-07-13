package com.heno.fullback.common.dto.builder;

import com.heno.fullback.common.dto.ErrorResource;

import java.time.LocalDateTime;

public class ErrorResourceBuilder {
	private LocalDateTime time;
	private String message;

	public ErrorResourceBuilder withTime(LocalDateTime time) {
		this.time = time;
		return this;
	}

	public ErrorResourceBuilder withMessage(String message) {
		this.message = message;
		return this;
	}

	public ErrorResource createErrorResource() {
		return new ErrorResource(time, message);
	}
}