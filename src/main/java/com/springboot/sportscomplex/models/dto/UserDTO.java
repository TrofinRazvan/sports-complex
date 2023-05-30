package com.springboot.sportscomplex.models.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "First name must be between 3 and 15.")
    private String firstName;
    @NotBlank(message = "Last name must be between 3 and 15.")
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
//    @NotBlank
//    private String sms;
}