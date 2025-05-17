package com.pkr.spring.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.pkr.spring.models.Doctor;

@Service
public interface DoctorService {

	public Doctor addDoctor(Doctor doctor);
	public Page<Doctor> getAllDoctor(int page, int size);
	public Doctor getDoctorById(Long id);
	public Doctor updateDoctor(Long id, Doctor doctor);
	public void deleteDoctor(Long id);
}