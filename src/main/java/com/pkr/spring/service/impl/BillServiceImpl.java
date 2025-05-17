package com.pkr.spring.service.impl;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pkr.spring.models.Bill;
import com.pkr.spring.repository.BillRepo;
import com.pkr.spring.service.BillService;

public class BillServiceImpl implements BillService{

	private static final Logger logger = LoggerFactory.getLogger(BillService.class);
	
	@Autowired
	BillRepo billRepo;
	
	@Override
	public Bill addBill(Bill bill) {
		try {
			billRepo.save(bill);
			return bill;
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while adding bill: {}",e.getMessage());
			return null;
		}
	}
	
	@Override
	public Page<Bill> getAllBill(int page,int size) {
		try {
			Pageable pageable = PageRequest.of(page, size);
			return billRepo.findAll(pageable);
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while fetching bills: {}",e.getMessage());
			return null;
		}
	}
	
	@Override
	public Bill getBillById(Long id) {
		try {
			Optional<Bill> bill = billRepo.findById(id);
			return bill.orElse(null);
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while fetching bill by id {}: {}",id,e.getMessage());
			return null;
		}
	}
	
	@Override
	public Bill updateBill(Long id, Bill bill) {
		try {
			Optional<Bill> oldBill = billRepo.findById(id);
			if(oldBill.isPresent()) {
				Bill newBill = oldBill.get();
				newBill.setAmount(bill.getAmount());
				newBill.setPatientId(bill.getPatientId());
				newBill.setStatus(bill.getStatus());
				billRepo.save(newBill);
				return newBill;
			}
			else {
				return null;
			}
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while updating bill: {}",e.getMessage());
			return null;
		}
	}
	
	@Override
	public void deleteBill(Long id) {
		try {
			
			logger.info("bill deleted");
			billRepo.deleteById(id);
			
			
		}catch(Exception e) {
			System.out.println("Error Message: "+e.getMessage());
			logger.error("Error occured while adding bill: {}",e.getMessage());
			
		}
	}
}
