package com.pkr.spring.service.impl;

import com.pkr.spring.service.PatientService;



import com.pkr.spring.models.Patient;
import com.pkr.spring.repository.PatientRepo;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PatientServiceImpl implements PatientService{
	
	private static final Logger logger =  LoggerFactory.getLogger(PatientService.class);
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Override
	public Patient addPatient(Patient patient) {
		try {
			patientRepo.save(patient);
			return patient;
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while adding patient: {}",e.getMessage());
			return null;
		}
		
	}
	
	@Override
	public ResponseEntity<Page<Patient>> getAllPatient(int page, int size) {
		try {
			Pageable pageable = PageRequest.of(page, size);
			System.out.println(10/0);
			return new ResponseEntity<>(patientRepo.findAll(pageable), HttpStatus.OK);
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while getting all patient: {}",e.getMessage());
			return null;
		}
	}
	
	@Override
	public Patient getPatientById(Long id) {
		try {
			Optional<Patient> patient = patientRepo.findById(id);
			
			return patient.orElse(null);
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while getting patient by id: {}",e.getMessage());
			return null;
		}
	}
	
	@Override
	public Patient updatePatient(Long id, Patient patient) {
		try {
			Optional<Patient> oldPatient = patientRepo.findById(id);
			if(oldPatient.isPresent()) {
				Patient newPatient = oldPatient.get();
				newPatient.setName(patient.getName());
				newPatient.setAge(patient.getAge());
				newPatient.setGender(patient.getGender());
				patientRepo.save(newPatient);
				return newPatient;
				
			}else {
				logger.error("Patient with id {} not found",id);
				return null;
			}
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while updating patient: {}",e.getMessage());
			return null;
		}
	}
	
	@Override
	public void deletePatient(Long id) {
		try {
			
			logger.info("deleting patient by id {}",id);
			patientRepo.deleteById(id);
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while removing patient: {}",e.getMessage());
			
		}
	}

}
