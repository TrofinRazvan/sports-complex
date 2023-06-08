package com.springboot.sportscomplex.services.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.sportscomplex.exceptions.PhoneNumberTakenException;
import com.springboot.sportscomplex.exceptions.UserNotFoundException;
import com.springboot.sportscomplex.models.dto.UserDTO;
import com.springboot.sportscomplex.models.entities.UserEntity;
import com.springboot.sportscomplex.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ObjectMapper objectMapper,
                           UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Optional<UserEntity> userEntityOptional = userRepository.findByPhoneNumber(userDTO.getPhoneNumber());
        if (userEntityOptional.isPresent()) {
            throw new PhoneNumberTakenException("Phone number: " + userDTO.getPhoneNumber() + " already exists.");
        }
        UserEntity userEntity = objectMapper.convertValue(userDTO, UserEntity.class);
        UserEntity savedUser = userRepository.save(userEntity);
        log.info("User " + savedUser.getFirstName() + " " + savedUser.getLastName() + " was created" + " with the number " + savedUser.getPhoneNumber() + ".");
        return objectMapper.convertValue(savedUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> usersFound = userRepository.findAll();
        List<UserDTO> usersFoundDTO = new ArrayList<>();
        usersFound.forEach(userEntity -> usersFoundDTO.add(objectMapper.convertValue(userEntity, UserDTO.class)));
        return usersFoundDTO;
    }

    @Override
    public UserDTO findByPhoneNumber(String phoneNumber) {
        Optional<UserEntity> userEntityOptional = userRepository.findByPhoneNumber(phoneNumber);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();
            log.info("User found with the phone number: " + phoneNumber);
            return objectMapper.convertValue(userEntity, UserDTO.class);
        }
        log.info("No user found with the phone number: " + phoneNumber);
        return null;
    }

    @Override
    public void deleteUserById(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info("User with id " + id + " was successfully deleted.");
        } else {
            throw new UserNotFoundException("User not found.");
        }
    }

    @Override
    public UserDTO updateUserById(long id, UserDTO userDTO) {
        UserEntity userFound = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + "does not exist."));
        userFound.setFirstName(userDTO.getFirstName());
        userFound.setLastName(userDTO.getLastName());
        userFound.setEmail(userDTO.getEmail());
        userFound.setPhoneNumber(userDTO.getPhoneNumber());
        UserEntity customerSaved = userRepository.save(userFound);
        log.info("Customer with id " + id + " was successfully updated");
        return objectMapper.convertValue(customerSaved, UserDTO.class);
    }
}