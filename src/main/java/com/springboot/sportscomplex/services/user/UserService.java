package com.springboot.sportscomplex.services.user;

import com.springboot.sportscomplex.models.dto.UserDTO;
import com.springboot.sportscomplex.models.entities.UserEntity;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<UserEntity> createUser(UserDTO userDTO);


}
