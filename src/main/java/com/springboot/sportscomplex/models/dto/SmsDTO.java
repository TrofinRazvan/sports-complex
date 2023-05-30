package com.springboot.sportscomplex.models.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SmsDTO {

    @Pattern(regexp = "\\d{10}", message = "Numarul trebuie sa contina 10 cifre.")
    private String phoneNumber;
    @Size(max = 200, message = "Mesajul trebuie sa aiba maxim {max} caractere.")
    private String sms;
}
