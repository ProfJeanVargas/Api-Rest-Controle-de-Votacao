package com.sistema.votacao.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorApiMessage {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime 	timestamp;
	private Integer 		code;
	private HttpStatus 		status;
	private String 			message;

	public ErrorApiMessage(Integer code, HttpStatus status, String message) {
		timestamp 	= LocalDateTime.now();
		this.setCode(code);
		this.setStatus(status);
		this.setMessage(message);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}