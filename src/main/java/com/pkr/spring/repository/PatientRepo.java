package com.pkr.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pkr.spring.models.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient,Long>{
	
	
}
