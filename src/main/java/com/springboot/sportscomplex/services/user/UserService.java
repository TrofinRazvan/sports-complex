package com.springboot.sportscomplex.services.user;

import com.springboot.sportscomplex.models.dto.UserDTO;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO findByPhoneNumber(String phoneNumber);
}