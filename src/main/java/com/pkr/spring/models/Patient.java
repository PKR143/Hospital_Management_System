package com.pkr.spring.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private LocalDate dob;
	private Integer age;
	private String gender;
	private String disease;
	private String maritalStatus;
	private String address;
	private String city;
	private String state;
	private String country;
	private Integer pin;
	private String mailId;
	private String contactNum;
	private String emergencyContactNum;
	private String bloodGroup;
	private LocalDate createdAt;
	private LocalDate updatedAt; 	
	
}
