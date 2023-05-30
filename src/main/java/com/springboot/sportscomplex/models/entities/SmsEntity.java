package com.springboot.sportscomplex.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sms")
public class SmsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "phone_Number")
    private String phoneNumber;
    @Column(name = "sms")
    private String sms;
}
