package com.springboot.sportscomplex.unitTesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.sportscomplex.models.dto.UserDTO;
import com.springboot.sportscomplex.models.entities.User;
import com.springboot.sportscomplex.repositories.UserRepository;
import com.springboot.sportscomplex.services.user.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
public class PhoneNumberTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private UserRepository userRepository;

    @Test
    void testFindByPhoneNumber() {
        //Given
        String phoneNumber = "0755412563";
        UserDTO userDTO = UserDTO.builder()
                .firstName("Georgescu")
                .lastName("Ionel")
                .email("geo.ion@yahoo.com")
                .phoneNumber(phoneNumber)
                .build();
        User user = User.builder()
                .firstName("Georgescu")
                .lastName("Ionel")
                .email("geo.ion@yahoo.com")
                .phoneNumber(phoneNumber)
                .build();
        when(userRepository.findUserByPhoneNumber(phoneNumber)).thenReturn(Optional.of(user));
        when(objectMapper.convertValue(user, UserDTO.class)).thenReturn(userDTO);
        // When
        UserDTO resultUserDTO = userService.findUserByPhoneNumber(phoneNumber);
        //Then
        Assertions.assertEquals(userDTO, resultUserDTO);
    }
}
