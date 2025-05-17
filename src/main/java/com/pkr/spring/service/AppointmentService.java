package com.pkr.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import com.pkr.spring.models.Appointment;

@Service
public interface AppointmentService {

	public Appointment addAppointment(Appointment app);
	public Page<Appointment> getAllAppointment(int page, int size);
	public Appointment getAppointmentById(Long id);
	public Appointment updateAppointment(Long id, Appointment appointment);
	public void deleteAppointment(Long id);
	
}
