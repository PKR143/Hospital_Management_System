package com.pkr.spring.service.impl;

import com.pkr.spring.service.AppointmentService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pkr.spring.models.Appointment;
import com.pkr.spring.repository.AppointmentRepo;

public class AppointmentServiceImpl implements AppointmentService{
	
	
	private static final Logger logger = LoggerFactory.getLogger(Appointment.class);
	
	@Autowired
	private AppointmentRepo appRepo;
	
	@Override
	public Appointment addAppointment(Appointment app) {
		try {
			appRepo.save(app);
			return app;
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while adding Appointment: {}",e.getMessage());
			return null;
		}
	}
	
	@Override
	public Appointment getAppointmentById(Long id) {
		try {
			Optional<Appointment> app = appRepo.findById(id);
			return app.orElse(null);
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while fetching Appointment by id{}: {}",id,e.getMessage());
			return null;
		}
	}
	
	@Override
	public Page<Appointment> getAllAppointment(int page, int size) {
		try {
			Pageable pageable = PageRequest.of(page, size);
			return appRepo.findAll(pageable);
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while fetching Appointments: {}",e.getMessage());
			return null;
		}
	}
	
	@Override
	public Appointment updateAppointment(Long id, Appointment app) {
		try {
			Optional<Appointment> oldApp = appRepo.findById(id);
			if(oldApp.isPresent()){
				Appointment newApp = oldApp.get();
				newApp.setDate(app.getDate());
				newApp.setDoctorId(app.getDoctorId());
				newApp.setPatientId(app.getPatientId());
				appRepo.save(newApp);
				return newApp;
			}else {
				logger.error("appointment not found");
				return null;
			}
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while updating Appointment: {}",e.getMessage());
			return null;
		}
	}
	
	@Override
	public void deleteAppointment(Long id) {
		try {
			logger.info("Appointment deleted");
			appRepo.deleteById(id);
			
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while adding Appointment: {}",e.getMessage());
			
		}
	}
}
