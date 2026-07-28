package com.pkr.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data @Builder @AllArgsConstructor @RequiredArgsConstructor
public class HandleExceptionResponse {
	private String response;
	private Long statusCode;
	private String statusDesc;
}
