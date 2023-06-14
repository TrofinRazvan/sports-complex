package com.springboot.sportscomplex.services.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.sportscomplex.exceptions.PhoneNumberTakenException;
import com.springboot.sportscomplex.exceptions.UserNotFoundException;
import com.springboot.sportscomplex.models.dto.UserDTO;
import com.springboot.sportscomplex.models.entities.User;
import com.springboot.sportscomplex.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
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
        try {
            Optional<User> userEntityOptional = userRepository.findUserByPhoneNumber(userDTO.getPhoneNumber());
            if (userEntityOptional.isPresent()) {
                throw new PhoneNumberTakenException("Phone number: " + userDTO.getPhoneNumber() + " already exists.");
            }
            User user = objectMapper.convertValue(userDTO, User.class);
            User savedUser = userRepository.save(user);
            log.info("User " + savedUser.getFirstName() + " " + savedUser.getLastName() + " was created" + " with the number " + savedUser.getPhoneNumber() + ".");
            return objectMapper.convertValue(savedUser, UserDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("Error occurred while creating user: " + e.getMessage());
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> usersFound = userRepository.findAll();
        List<UserDTO> usersFoundDTO = new ArrayList<>();
        usersFound.forEach(userEntity -> usersFoundDTO.add(objectMapper.convertValue(userEntity, UserDTO.class)));
        return usersFoundDTO;
    }

    @Override
    public UserDTO findUserByPhoneNumber(String phoneNumber) {
        Optional<User> userEntityOptional = userRepository.findUserByPhoneNumber(phoneNumber);
        if (userEntityOptional.isPresent()) {
            User user = userEntityOptional.get();
            log.info("User found with the phone number: " + phoneNumber);
            return objectMapper.convertValue(user, UserDTO.class);
        }
        log.info("No user found with the phone number: " + phoneNumber);
        return null;
    }
    public long getUserCount() {
        return userRepository.count();
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
        User userFound = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + "does not exist."));
        userFound.setFirstName(userDTO.getFirstName());
        userFound.setLastName(userDTO.getLastName());
        userFound.setEmail(userDTO.getEmail());
        userFound.setPhoneNumber(userDTO.getPhoneNumber());
        userFound.setDateOfBirth(userDTO.getDateOfBirth());
        userFound.setGender(userDTO.getGender());
        userFound.setAddress(userDTO.getAddress());
        userFound.setCity(userDTO.getCity());
        userFound.setEmergencyContactName(userDTO.getEmergencyContactName());
        userFound.setEmergencyContactPhoneNumber(userDTO.getEmergencyContactPhoneNumber());
        User customerSaved = userRepository.save(userFound);
        log.info("Customer with id " + id + " was successfully updated");
        return objectMapper.convertValue(customerSaved, UserDTO.class);
    }
}