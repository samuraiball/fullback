package com.heno.fullback.common.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Error Resource
 * @author hirooka
 */
public class ErrorResource implements Serializable {


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
