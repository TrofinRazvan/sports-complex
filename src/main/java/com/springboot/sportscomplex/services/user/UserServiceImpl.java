package com.springboot.sportscomplex.services.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.sportscomplex.exceptions.PhoneNumberTakenException;
import com.springboot.sportscomplex.models.dto.UserDTO;
import com.springboot.sportscomplex.models.entities.UserEntity;
import com.springboot.sportscomplex.repositories.UserRepository;
import com.springboot.sportscomplex.services.sms.SmsValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final SmsValidationService smsValidationService;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(SmsValidationService smsValidationService,
                           UserRepository userRepository) {
        this.userRepository = userRepository;
        this.smsValidationService = smsValidationService;
    }

    @Override
    public ResponseEntity<UserEntity> createUser(UserDTO userDTO) {

        // Step one: Verific daca user-ul exista in baza de date
        Optional<UserEntity> userEntityOptional = userRepository.findByPhoneNumber(userDTO.getPhoneNumber());

        // Step 2.a: Daca exista trebuie sa arunce o exceptie
        if (userEntityOptional.isPresent()) {
            throw new PhoneNumberTakenException("Phone number: " + userDTO.getPhoneNumber() + " already exist.");
        }
        // Step 2.b: Daca nu exista se salveaza in baza de date
        UserEntity saveUser = userRepository.save(userEntityOptional.get());
        return ResponseEntity.ok(saveUser);
    }

//        smsValidationService.smsValidation(userDTO.getSms());
//        UserEntity userEntity = objectMapper.convertValue(userDTO, UserEntity.class);
//        UserEntity savedUser = userRepository.save(userEntity);
//        log.info("User " + savedUser.getSms());
//        return objectMapper.convertValue(savedUser, UserDTO.class);
    }


    //    @Override
//    public UserDTO deleteUserById(String name) {
//        for(UserDTO user : userDTOList) {
//            if(user.getFullName().equalsIgnoreCase(name)) {
//                userDTOList.remove();
//            }
//        }
//        return null;
//    }
