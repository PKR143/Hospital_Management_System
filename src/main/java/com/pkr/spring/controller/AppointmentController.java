package com.pkr.spring.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pkr.spring.models.Appointment;
import com.pkr.spring.service.AppointmentService;


@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@PostMapping("/add")
	public Appointment addAppointment(@RequestBody Appointment app) {
		return appointmentService.addAppointment(app);
		
	}
	
	@GetMapping("/get")
	public Page<Appointment> getAllAppointment(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2")int size) {
		return appointmentService.getAllAppointment(page,size);
	}
	
	@GetMapping("/get/{id}")
	public Appointment getAppointmentById(@PathVariable Long id) {
		return appointmentService.getAppointmentById(id);
	}
	
	@PutMapping("/update/{id}")
	public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
		return appointmentService.updateAppointment(id, appointment);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteAppointment(@PathVariable Long id) {
		appointmentService.deleteAppointment(id);
	}
}
