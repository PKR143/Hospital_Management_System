package com.pkr.spring.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pkr.spring.models.Bill;
import com.pkr.spring.service.BillService;

@RestController
@RequestMapping("/api/v1/bills")
public class BillController {
	
	@Autowired
	private BillService billService;
	
	@PostMapping("/add")
	public Bill addBill(@RequestBody Bill bill) {
		return billService.addBill(bill);
	}
	
	@GetMapping("/get")
	public Page<Bill> getAllBill(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2")int size) {
		return billService.getAllBill(page,size);
	}
	
	@GetMapping("/get/{id}")
	public Bill getBillById(@PathVariable Long id) {
		return billService.getBillById(id);
	}
	
	@PutMapping("/update/{id}")
	public Bill updateBill(@PathVariable Long id, @RequestBody Bill bill) {
		return billService.updateBill(id, bill);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteBill(@PathVariable Long id) {
		billService.deleteBill(id);
	}
	
}
