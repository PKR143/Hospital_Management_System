package com.pkr.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pkr.spring.service.AppointmentService;
import com.pkr.spring.service.BillService;
import com.pkr.spring.service.DoctorService;
import com.pkr.spring.service.PatientService;
import com.pkr.spring.service.impl.AppointmentServiceImpl;
import com.pkr.spring.service.impl.BillServiceImpl;
import com.pkr.spring.service.impl.DoctorServiceImpl;
import com.pkr.spring.service.impl.PatientServiceImpl;

@Configuration
public class BeanConfiguration {

	@Bean
	PatientService getPatientService() {
		return new PatientServiceImpl();
	}
	
	@Bean
	DoctorService getDoctorService() {
		return new DoctorServiceImpl();
	}
	
	@Bean
	BillService getBillService() {
		return new BillServiceImpl();
	}
	
	@Bean
	AppointmentService getAppointment() {
		return new AppointmentServiceImpl();
	}
}
