package com.pkr.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pkr.spring.models.Doctor;

@Service
public interface DoctorService {

	public ResponseEntity<Doctor> addDoctor(Doctor doctor);
	public ResponseEntity<Page<Doctor>> getAllDoctor(int page, int size);
	public ResponseEntity<Doctor> getDoctorById(Long id);
	public ResponseEntity<Doctor> updateDoctor(Long id, Doctor doctor);
	public ResponseEntity<String> deleteDoctor(Long id);
}