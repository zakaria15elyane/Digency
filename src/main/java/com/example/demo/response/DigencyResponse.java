package com.example.demo.response;

import org.springframework.http.HttpStatus;
public class DigencyResponse extends org.springframework.http.ResponseEntity<Object> {

	public DigencyResponse() {
		super(null);
		// TODO Auto-generated constructor stub
	}
	public DigencyResponse(HttpStatus status) {
		super(status);
		// TODO Auto-generated constructor stub
	}

	public DigencyResponse(Object object, HttpStatus status) {
		super(object, status);
		// TODO Auto-generated constructor stub
	}
}
