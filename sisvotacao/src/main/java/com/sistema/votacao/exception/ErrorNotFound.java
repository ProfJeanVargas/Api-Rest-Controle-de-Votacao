package com.sistema.votacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErrorNotFound extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorNotFound(String message) {
		super(message);
	}
}
