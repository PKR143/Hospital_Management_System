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

public class DoctorServiceImpl implements DoctorService{

	private static final Logger logger = LoggerFactory.getLogger(DoctorService.class);
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	@Override
	public Doctor addDoctor(Doctor doctor) {
		try {
			doctorRepo.save(doctor);
			return doctor;
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while adding doctor: {}",e.getMessage());
			return null;
		}
		
	}
	
	@Override
	public Page<Doctor> getAllDoctor(int page,int size) {
		try {
			Pageable pageable = PageRequest.of(page, size);
			return doctorRepo.findAll(pageable);
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while fetching doctors: {}",e.getMessage());
			return null;
		}
	}
	
	@Override
	public Doctor getDoctorById(Long id) {
		try {
			Optional<Doctor> doctor = doctorRepo.findById(id);
			return doctor.orElse(null);
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while fetching doctor by id {}: {}",id,e.getMessage());
			return null;
		}
	}
	
	@Override
	public Doctor updateDoctor(Long id, Doctor doctor) {
		try {
			Optional<Doctor> oldDoctor = doctorRepo.findById(id);
			if(oldDoctor.isPresent()) {
				Doctor newDoctor = oldDoctor.get();
				newDoctor.setAge(doctor.getAge());
				newDoctor.setName(doctor.getName());
				newDoctor.setSpeciality(doctor.getSpeciality());
				doctorRepo.save(newDoctor);
				return newDoctor;
			}else {
				logger.error("doctor with id {} not found",id);
				return null;
			}
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while updating doctor: {}",e.getMessage());
			return null;
		}
	}
	
	@Override
	public void deleteDoctor(Long id) {
		try {
			logger.info("Doctor deleted with {}",id);
			doctorRepo.deleteById(id);
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while adding doctor: {}",e.getMessage());
			
		}
	}
	
}
