package com.springboot.sportscomplex.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters.")
    private String username;
    @NotNull(message = "Email is a mandatory field.")
    @NotEmpty(message = "Email can't be empty.")
    private String email;
    @NotNull
    @NotEmpty
    private String fullName;


}
