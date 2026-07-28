package com.pkr.spring.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pkr.spring.dto.PatientRequest;
import com.pkr.spring.dto.PatientResponse;
import com.pkr.spring.exception.HospitalException;
import com.pkr.spring.models.Patient;
import com.pkr.spring.service.PatientService;
import com.pkr.spring.util.HospitalUtil;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/patients")
@Slf4j
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addPatient(@RequestBody @Valid PatientRequest patient) throws HospitalException{
		log.info("Validating Patient add request: {}",patient);
		if(patient == null) {
			log.info("Patient is missing in the request.");
			throw new HospitalException("Please provide patient details for registration.");
		}
		
		
		PatientResponse response =  patientService.addPatient(patient);
		
		if(!response.getStatusDesc().equalsIgnoreCase("SUCCESS")) {
			log.info("Something went wrong while registring the patient: {}",patient.getName());
			throw new HospitalException("Something went wrong, please try again after some times.");
		}
		response.setStatusDesc("Patient registered successfully.");
		log.info("Patient registrated successfully, {}",patient);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Page<Patient>> getAllPatient(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2")int size){
		return patientService.getAllPatient(page,size);
	}
	
	@GetMapping("/get/{id}")	
	public ResponseEntity<?> getPatientById(@PathVariable Long id) {
		log.info("Validating search request for id: {}",id);
		if(id == null) {
			log.info("Id is missing in the request.");
			throw new HospitalException("Please provide the id of patient.");
		}
		PatientResponse response = patientService.getPatientById(id);
		
		if(!response.getStatusDesc().equalsIgnoreCase("SUCCESS")) {
			log.info("{} Patient not exist in DB",id);
			throw new HospitalException("Patient not exist with id: "+id);
		}
		log.info("{} Patient exists.",id);
		response.setStatusDesc("Patient Found Successfully.");
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable Long id, @RequestBody @Valid PatientRequest patient) {
		log.info("Validating patient update request for id: {}",id);
		if(patient == null) {
			log.info("Patient details are missing in the request");
			throw new HospitalException("Please enter Patient details");
		}
		PatientResponse response = patientService.updatePatient(id, patient);
		if(response == null || !response.getStatusDesc().equalsIgnoreCase("SUCCESS")) {
			log.info("{} Patient not exists in DB",id);
			throw new HospitalException("Patient not exist with id: "+id);
		}
		log.info("Patient updated successfully");
		response.setStatusDesc("Patient updated successfully.");
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletePatient(@PathVariable Long id) {
		log.info("Validating delete request for patient: {}",id);
		if(id == null || id < 0) {
			log.info("Id is missing or invalid id in the request");
			throw new HospitalException("Please enter a valid id.");
		}
		patientService.deletePatient(id);
		
	}
}
