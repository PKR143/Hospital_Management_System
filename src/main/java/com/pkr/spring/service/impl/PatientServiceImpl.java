package com.pkr.spring.service.impl;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pkr.spring.dto.PatientRequest;
import com.pkr.spring.dto.PatientResponse;
import com.pkr.spring.exception.HospitalException;
import com.pkr.spring.models.Patient;
import com.pkr.spring.repository.PatientRepo;
import com.pkr.spring.service.PatientService;
import com.pkr.spring.util.HospitalUtil;

public class PatientServiceImpl implements PatientService{
	
	private static final Logger logger =  LoggerFactory.getLogger(PatientService.class);
	
	@Autowired
	private PatientRepo patientRepo;
	
	@Override
	public PatientResponse addPatient(PatientRequest patient) {
		try {
			logger.info("Processing patient registrationn request.");
			Patient entity = HospitalUtil.mapToPatientEntity(patient);
			
			entity.setCreatedAt(LocalDate.now());
			entity.setUpdatedAt(LocalDate.now());
			patientRepo.save(entity);
			logger.info("Patient registerd successfully.");
			return HospitalUtil.mapTpPatientResponse(entity, 1L, "SUCCESS");
			
		}catch(Exception e) {
			logger.error("Error occured while adding patient: {}",e.getMessage());
			throw e;
		}
		
	}
	
	@Override
	public ResponseEntity<Page<Patient>> getAllPatient(int page, int size) throws HospitalException{
		try {
			Pageable pageable = PageRequest.of(page, size);
			logger.info("---------------------Retrieving patients------------------------");
			return new ResponseEntity<>(patientRepo.findAll(pageable), HttpStatus.OK);
			
		}catch(Exception e) {
			logger.error("Error occured while getting all patient: {}",e.getMessage());
			throw new HospitalException("Something went worng, please try after some times");
			
		}
	}
	
	@Override
	public PatientResponse getPatientById(Long id) {
		try {
			logger.info("Processing patient search request with id: {}",id);
			Optional<Patient> optPatient = patientRepo.findById(id);
			
			if(optPatient.isEmpty()) {
				logger.info("Patient with id {} not exists.",id);
				return HospitalUtil.mapTpPatientResponse(null, -1L, "ERROR");
			}
			Patient patient = optPatient.get();
			logger.info("Patient {} exists in db {}",id,patient.getName());
			return HospitalUtil.mapTpPatientResponse(patient, 1L, "SUCCESS");
			
		}
		catch(Exception e) {
			logger.error("Error occured while getting patient by id: {}",e.getMessage());
			throw new HospitalException(id+" Patient not exists!");
		}
	}
	
	@Override
	public PatientResponse updatePatient(Long id, PatientRequest request) {
		try {
			logger.info("Processing patient update request with id: {} and update request: {}",id, request);
			Optional<Patient> oldPatient = patientRepo.findById(id);
			
			if(oldPatient.isEmpty()) {
				logger.info("Patinet not exists in DB");
				return HospitalUtil.mapTpPatientResponse(null, -1L, "ERROR");
			}
			
			Patient entity = oldPatient.get();
			entity = HospitalUtil.mapToPatientEntityForUpdate(entity, request);
			patientRepo.save(entity);
			logger.info("{} Patient updated successfully.",id);
			
			return HospitalUtil.mapTpPatientResponse(entity, 1L, "SUCCESS");
			
		}catch(Exception e) {
			logger.error("Error occured while updating patient: {}",e.getMessage());
			throw new HospitalException("Something went wrong, please try again after some times.");
		}
	}
	
	@Override
	public void deletePatient(Long id) {
		try {
			logger.info("processing the delete request of patient: {}",id);
			
			logger.info("deleting patient by id {}",id);
			patientRepo.deleteById(id);
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while removing patient: {}",e.getMessage());
		}
	}

}
