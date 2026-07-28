package com.pkr.spring.util;

import java.time.LocalDate;

import com.pkr.spring.dto.PatientRequest;
import com.pkr.spring.dto.PatientResponse;
import com.pkr.spring.models.Patient;

public class HospitalUtil {
		
	public static Patient mapToPatientEntity(PatientRequest request) {
		return Patient.builder()
				.name(request.getName())
				.dob(request.getDob())
				.address(request.getAddress()).disease(request.getDisease())
				.age( LocalDate.now().getYear() - request.getDob().getYear())
				.address(request.getAddress())
				.maritalStatus(request.getMaritalStatus())
				.gender(request.getGender())
				.city(request.getCity())
				.state(request.getState())
				.country(request.getCountry())
				.bloodGroup(request.getBloodGroup())
				.pin(request.getPin())
				.mailId(request.getMailId())
				.contactNum(request.getContactNum())
				.emergencyContactNum(request.getContactNum())
				.build();
	}

	public static PatientResponse mapTpPatientResponse(Patient patient, Long code, String msg) {
		return PatientResponse.builder().patient(patient).statusCode(code).statusDesc(msg)
				.build();
	}
	
	public static Patient mapToPatientEntityForUpdate(Patient entity, PatientRequest request) {
		entity.setName(request.getName());
		entity.setDob(request.getDob());
		entity.setAddress(request.getAddress());
		entity.setDisease(request.getDisease());
		entity.setAge(LocalDate.now().getYear() - request.getDob().getYear());
		entity.setMaritalStatus(request.getMaritalStatus());
		entity.setGender(request.getGender());
		entity.setCity(request.getCity());
		entity.setState(request.getState());
		entity.setCountry(request.getCountry());
		entity.setBloodGroup(request.getBloodGroup());
		entity.setPin(request.getPin());
		entity.setMailId(request.getMailId());
		entity.setContactNum(request.getContactNum());
		entity.setEmergencyContactNum(request.getEmergencyContactNum()); 
		entity.setUpdatedAt(LocalDate.now());
		return entity;
	}
	
}
