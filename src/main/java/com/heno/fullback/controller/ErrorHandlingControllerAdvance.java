package com.heno.fullback.controller;

import com.heno.fullback.model.common.ErrorResource;
import com.heno.fullback.model.common.builder.ErrorResourceBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Error Handling Controller Advance
 */
@ControllerAdvice
public class ErrorHandlingControllerAdvance extends ResponseEntityExceptionHandler {

	private final static String DEFAULT_ERROR_MESSAGE = "Something happened";


	//FIXME:その他のエラーをハンドリングする。
	private final Map<Class<? extends Exception>, String>
			messageMapping = Collections
			.unmodifiableMap(new LinkedHashMap() {{
				put(HttpMessageNotReadableException.class, "Request body is invalid");
				put(NoHandlerFoundException.class, "Not Found");
			}});

	private final String errorMessageResolver(Exception ex, String defaultMessage) {
		return messageMapping.entrySet().stream()
				.filter(entry -> entry.getKey().isAssignableFrom(ex.getClass()))
				.findFirst()
				.map(Map.Entry::getValue)
				.orElse(defaultMessage);
	}

	private ErrorResource createErrorResource(Exception ex) {
		return new ErrorResourceBuilder()
				.withMessage(errorMessageResolver(ex, DEFAULT_ERROR_MESSAGE))
				.withTime(LocalDateTime.now())
				.createErrorResource();
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex,
			Object body,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request
	) {
		ErrorResource er = createErrorResource(ex);
		return super.handleExceptionInternal(ex, er, headers, status, request);
	}
}
