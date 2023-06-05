package com.springboot.sportscomplex.services.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.sportscomplex.exceptions.PhoneNumberTakenException;
import com.springboot.sportscomplex.models.dto.UserDTO;
import com.springboot.sportscomplex.models.entities.UserEntity;
import com.springboot.sportscomplex.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // 1. Verific daca user-ul exista in baza de date
        Optional<UserEntity> userEntityOptional = userRepository.findByPhoneNumber(userDTO.getPhoneNumber());
        // 2. Daca exista trebuie sa arunce o exceptie
        if (userEntityOptional.isPresent()) {
            throw new PhoneNumberTakenException("Phone number: " + userDTO.getPhoneNumber() + " already exists.");
        }
        // 3. Daca nu se face maparea (din UserDTO in UserEntity)
        UserEntity userEntity = objectMapper.convertValue(userDTO, UserEntity.class);
        // 4. Se salveaza in baza de date
        UserEntity savedUser = userRepository.save(userEntity);
        log.info("User " + savedUser.getFirstName() + " " + savedUser.getLastName() + " was created" + " with the number " + savedUser.getPhoneNumber() + ".");
        return objectMapper.convertValue(savedUser, UserDTO.class);
    }

    @Transactional
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
}