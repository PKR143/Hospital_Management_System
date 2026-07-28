package com.pkr.spring.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pkr.spring.models.Doctor;
import com.pkr.spring.repository.DoctorRepo;
import com.pkr.spring.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DoctorServiceImpl implements DoctorService{

	private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Override
	public ResponseEntity<Doctor> addDoctor(Doctor doctor) {
		try {
			doctorRepo.save(doctor);
			return new ResponseEntity<>(doctor, HttpStatus.CREATED);
			
		}catch(Exception e) {

			logger.error("Error occured while adding doctor: {}",e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Override
	public ResponseEntity<Page<Doctor>> getAllDoctor(int page,int size) {
		try {
			Pageable pageable = PageRequest.of(page, size);
			return new ResponseEntity<>(doctorRepo.findAll(pageable),HttpStatus.OK);
			
		}catch(Exception e) {

			logger.error("Error occured while fetching doctors: {}",e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<Doctor> getDoctorById(Long id) {
		try {
			Optional<Doctor> doctor = doctorRepo.findById(id);
			if(doctor.isPresent())
				return new ResponseEntity<>(doctor.get(), HttpStatus.FOUND);
			else
				return new ResponseEntity<>(null , HttpStatus.NOT_FOUND);
			
		}catch(Exception e) {

			logger.error("Error occured while fetching doctor by id {}: {}",id,e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<Doctor> updateDoctor(Long id, Doctor doctor) {
		try {
			Optional<Doctor> oldDoctor = doctorRepo.findById(id);
			if(oldDoctor.isPresent()) {
				Doctor newDoctor = oldDoctor.get();
				newDoctor.setAge(doctor.getAge());
				newDoctor.setName(doctor.getName());
				newDoctor.setSpeciality(doctor.getSpeciality());
				doctorRepo.save(newDoctor);
				return new ResponseEntity<>(newDoctor, HttpStatus.ACCEPTED);
			}else {
				logger.error("doctor with id {} not found",id);
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
		}catch(Exception e) {

			logger.error("Error occured while updating doctor: {}",e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<String> deleteDoctor(Long id) {
		try {

			if(doctorRepo.findById(id).isPresent()){
				logger.info("Doctor deleted with id {}",id);
				doctorRepo.deleteById(id);
				return new ResponseEntity<>("Doctor deleted successfully!", HttpStatus.OK);
			}
			else{
				return new ResponseEntity<>("Doctor not found!", HttpStatus.NOT_FOUND);
			}
			
		}catch(Exception e) {

			logger.error("Error occured while adding doctor: {}",e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
