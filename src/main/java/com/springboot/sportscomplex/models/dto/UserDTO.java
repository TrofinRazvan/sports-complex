package com.springboot.sportscomplex.models.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class UserDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name must not be blank")
    @Pattern(regexp = "^[a-zA-Z\\-\\s]*$", message = "First name contains invalid characters")
    @Size(min = 2, max = 20, message = "First name must contain between 2 and 20 characters")
    private String firstName;
    @NotBlank(message = "Last name must not be blank")
    @Pattern(regexp = "^[a-zA-Z\\-\\s]*$", message = "Last name contains invalid characters")
    @Size(min = 2, max = 20, message = "Last name must contain between 2 and 20 characters")
    private String lastName;
    @NotBlank(message = "Email must not be blank")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Email is not valid")
    private String email;
    @NotBlank(message = "Phone number must not be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number")
    private String phoneNumber;
    @NotNull(message = "Date of birth must not be null")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
    @NotBlank(message = "Gender must not be blank")
    @Pattern(regexp = "^(Male|Female)$", message = "Invalid gender")
    private String gender;
    @NotBlank(message = "Address must not be blank.")
    private String address;
    @NotBlank(message = "City must not be blank.")
    private String city;
    @NotBlank(message = "Emergency contact name must not be blank.")
    private String emergencyContactName;
    @NotBlank(message = "Emergency contact phone number must not be blank")
    private String emergencyContactPhoneNumber;
}