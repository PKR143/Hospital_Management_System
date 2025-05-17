package com.pkr.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pkr.spring.models.Bill;

@Repository
public interface BillRepo extends JpaRepository<Bill,Long>{

}
