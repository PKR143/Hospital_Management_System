package com.pkr.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pkr.spring.models.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long>{

}
