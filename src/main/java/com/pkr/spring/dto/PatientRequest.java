package com.pkr.spring.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class PatientRequest {
	
	@NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name can be at most 100 characters long")
	@Pattern(regexp = "^[A-Za-z\\s]+$", message = "Please enter a valid name.")
    private String name;

    @NotNull(message = "Date of birth is required")
//    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", message = "Date must be valid and in the format YYYY-MM-DD")
    @Past(message = "Please enter a valid date of birth")
    private LocalDate dob;
//
//    @NotNull(message = "Age is required")
//    @Min(value = 0, message = "Age cannot be negative")
//    @Max(value = 130, message = "Age seems invalid")
//    private Integer age;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(?i)(male|female|other)$", message = "Gender must be Male, Female, or Other")
    private String gender;

    @Size(max = 255, message = "Disease name too long")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Please enter a valid Disease.")
    private String disease;

    @Pattern(regexp = "^(?i)(single|married|divorced|widowed)?$", message = "Invalid marital status")
    private String maritalStatus;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address too long")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Country is required")
    private String country;

    @NotNull(message = "PIN code is required")
    @Min(value = 100000, message = "PIN must be at least 6 digits")
    @Max(value = 999999, message = "PIN must be 6 digits")
    private Integer pin;

    @Email(message = "Invalid email format")
    private String mailId;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Contact number must be between 10 and 15 digits")
    private String contactNum;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Emergency contact number must be between 10 and 15 digits")
    private String emergencyContactNum;

    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Invalid blood group format")
    private String bloodGroup;
}
