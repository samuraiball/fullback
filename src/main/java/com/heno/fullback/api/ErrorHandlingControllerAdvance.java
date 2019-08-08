package com.heno.fullback.api;

import com.heno.fullback.common.exception.DataAlreadyExistsException;
import com.heno.fullback.common.exception.DataNotFoundException;
import com.heno.fullback.common.exception.ForbiddenException;
import com.heno.fullback.common.dto.ErrorResource;
import com.heno.fullback.common.dto.builder.ErrorResourceBuilder;
import org.seasar.doma.jdbc.OptimisticLockException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Error Handling Controller Advance
 *
 */
@ControllerAdvice
public class ErrorHandlingControllerAdvance extends ResponseEntityExceptionHandler {

	private final static String DEFAULT_ERROR_MESSAGE = "Something happened. Contact an Administrator";

	private final Map<Class<? extends Exception>, String>
			messageMapping = Collections
			.unmodifiableMap(new LinkedHashMap() {{
				put(HttpMessageNotReadableException.class, "Request body is invalid");
				put(NoHandlerFoundException.class, "Not Found");
				put(ForbiddenException.class, "The request is forbidden");
				put(OptimisticLockException.class, "The request is Conflicted");
				put(DataNotFoundException.class, "The Data which you requested is Not Found");
				put(DataAlreadyExistsException.class, "The Data which you POSTed already exists");
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

	@ExceptionHandler(ForbiddenException.class)
	protected ResponseEntity<Object> handleForbiddenException(ForbiddenException ex, WebRequest request) {
		return handleExceptionInternal(ex, createErrorResource(ex), new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}

	@ExceptionHandler(OptimisticLockException.class)
	protected ResponseEntity<Object> handleOptimisticLockException(OptimisticLockException ex, WebRequest request) {
		return handleExceptionInternal(ex, createErrorResource(ex), new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(DataNotFoundException.class)
	protected ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, createErrorResource(ex), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(DataAlreadyExistsException.class)
	protected ResponseEntity<Object> handleDataAlreadyExistsException(DataAlreadyExistsException ex, WebRequest request) {
		return handleExceptionInternal(ex, createErrorResource(ex), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	// Global Error Handling
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleOptimisticLockException(Exception ex, WebRequest request) {
		return handleExceptionInternal(ex, createErrorResource(ex), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
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
