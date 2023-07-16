package com.springboot.sportscomplex.services.user;

import com.springboot.sportscomplex.models.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO findUserByPhoneNumber(String phoneNumber);
    String getUserCount();

    void deleteUserById(long id);

    UserDTO updateUserById(long id, UserDTO userDTO);
}