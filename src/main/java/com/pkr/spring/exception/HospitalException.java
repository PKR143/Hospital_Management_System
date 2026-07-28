package com.pkr.spring.exception;

public class HospitalException extends RuntimeException{
	
	private String message;
	public HospitalException(String msg) {
		super(msg);
		message = msg;
	}
}
