package com.sistema.votacao.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerError extends ResponseEntityExceptionHandler {
	@ExceptionHandler(ErrorNotFound.class)
	public ResponseEntity<ErrorApiMessage> customHandleNotFound(Exception ex, WebRequest request) throws IOException {
		ErrorApiMessage error = new ErrorApiMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, ex.getMessage()) ;
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(ErrorFound.class)
	public ResponseEntity<ErrorApiMessage> customHandleFound(Exception ex, WebRequest request) throws IOException {
		ErrorApiMessage error = new ErrorApiMessage(HttpStatus.FOUND.value(), HttpStatus.FOUND, ex.getMessage()) ;
        return new ResponseEntity<>(error, HttpStatus.FOUND);
    }
	
	@ExceptionHandler(ErrorBadRequest.class)
	public ResponseEntity<ErrorApiMessage> customHandleBadRequest(Exception ex, WebRequest request) throws IOException {
		ErrorApiMessage error = new ErrorApiMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, ex.getMessage()) ;
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
