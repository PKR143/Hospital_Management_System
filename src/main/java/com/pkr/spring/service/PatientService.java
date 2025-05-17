package com.pkr.spring.service;



import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pkr.spring.models.Patient;

@Service
public interface PatientService {

	public Patient addPatient(Patient patient);
	public ResponseEntity<Page<Patient>> getAllPatient(int page, int size);
	public Patient getPatientById(Long id);
	public Patient updatePatient(Long id, Patient patient);
	public void deletePatient(Long id);
}
