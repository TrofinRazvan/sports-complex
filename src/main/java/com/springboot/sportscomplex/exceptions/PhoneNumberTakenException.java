package com.springboot.sportscomplex.exceptions;

import org.springframework.dao.DuplicateKeyException;

public class PhoneNumberTakenException extends DuplicateKeyException {

    public PhoneNumberTakenException(String phone) {
        super(phone);
    }
}
