package com.pkr.spring.service;

import org.springframework.stereotype.Service;

import com.pkr.spring.models.Bill;
import org.springframework.data.domain.Page;

@Service
public interface BillService {

	public Bill addBill(Bill bill);
	public Page<Bill> getAllBill(int page,int size);
	public Bill getBillById(Long id);
	public Bill updateBill(Long id, Bill bill);
	public void deleteBill(Long id);
}
