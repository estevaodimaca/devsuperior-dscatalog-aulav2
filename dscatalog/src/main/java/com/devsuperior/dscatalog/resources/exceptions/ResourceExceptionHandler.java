package com.devsuperior.dscatalog.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException entityNotFoundException,
			HttpServletRequest request) {
		StandardError standardError = new StandardError();
		standardError.setTimestamp(Instant.now());
		standardError.setStatus(HttpStatus.NOT_FOUND.value());
		standardError.setMessage(entityNotFoundException.getMessage());
		standardError.setError("Resource not Found");
		standardError.setPath(request.getRequestURI());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);

	}

}
