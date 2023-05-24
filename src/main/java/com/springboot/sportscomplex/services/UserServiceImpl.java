package com.springboot.sportscomplex.services;

import com.springboot.sportscomplex.models.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    List<UserDTO> userDTOList = new ArrayList<>();
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        userDTOList.add(userDTO);
        return userDTO;
    }

    @Override
    public List<UserDTO> getUsers() {
        return userDTOList;
    }
}
