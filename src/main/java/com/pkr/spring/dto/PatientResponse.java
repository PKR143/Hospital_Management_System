package com.pkr.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pkr.spring.models.Patient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @Builder @AllArgsConstructor @NoArgsConstructor @ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientResponse{
	
	private Patient patient;
	private Long statusCode;
	private String statusDesc;

}
