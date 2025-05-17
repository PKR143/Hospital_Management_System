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

import com.pkr.spring.models.Patient;
import com.pkr.spring.service.PatientService;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
	
	@Autowired
	PatientService patientService;
	
	@PostMapping("/add")
	public Patient addPatient(@RequestBody Patient patient) {
		return patientService.addPatient(patient);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Page<Patient>> getAllPatient(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2")int size){
		return patientService.getAllPatient(page,size);
	}
	
	@GetMapping("/get/{id}")
	public Patient getPatientById(@PathVariable Long id) {
		return patientService.getPatientById(id);
	}
	
	@PutMapping("/update/{id}")
	public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
		return patientService.updatePatient(id, patient);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
	}
}
