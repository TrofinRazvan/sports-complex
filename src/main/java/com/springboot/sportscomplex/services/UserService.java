package com.springboot.sportscomplex.services;

import com.springboot.sportscomplex.models.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getUsers();
}
