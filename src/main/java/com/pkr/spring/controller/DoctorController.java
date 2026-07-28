package com.pkr.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


import com.pkr.spring.models.Doctor;
import com.pkr.spring.service.DoctorService;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

	@Autowired
	DoctorService doctorService;
	
	@PostMapping("/add")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
		return doctorService.addDoctor(doctor);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Page<Doctor>> getAllDoctor(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2")int size) {
		return doctorService.getAllDoctor(page,size);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
		return doctorService.getDoctorById(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
		return doctorService.updateDoctor(id, doctor);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDoctor(@PathVariable Long id) {
		return doctorService.deleteDoctor(id);
	}
	
}
