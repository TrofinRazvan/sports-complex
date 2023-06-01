package com.springboot.sportscomplex.controllers;

import com.springboot.sportscomplex.models.dto.UserDTO;
import com.springboot.sportscomplex.models.entities.UserEntity;
import com.springboot.sportscomplex.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.createUser(userDTO);
    }


}









//    @PostMapping("/users")
//    public UserDTO createUser(@RequestBody UserDTO userDTO) {
//        return userService.createUser(userDTO);
//    }

//    @GetMapping("/users")
//    public List<UserDTO> getUsers() {
//        return userService.getUsers();
//    }
//    @DeleteMapping("/users/{name}")
//    public UserDTO deleteUserById(@PathVariable Long id) {
//        userService.deleteUserById(id);
//        return UserDTO;
//    }