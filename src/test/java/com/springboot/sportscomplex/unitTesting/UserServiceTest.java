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

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private UserRepository userRepository;

    @Test
    void testCreateUsersShouldPass() {
        // Given
        UserDTO userDTO = UserDTO.builder()
                .firstName("Alexandrescu")
                .lastName("Robert")
                .email("alex.robert@yahoo.com")
                .phoneNumber("0756215485")
                .build();
        User user = User.builder()
                .firstName("Alexandrescu")
                .lastName("Robert")
                .email("alex.robert@yahoo.com")
                .phoneNumber("0756215485")
                .build();
        when(objectMapper.convertValue(userDTO, User.class)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(objectMapper.convertValue(user, UserDTO.class)).thenReturn(userDTO);
        // When
        UserDTO resultUserDTO = userService.createUser(userDTO);
        // Then
        Assertions.assertEquals(userDTO, resultUserDTO);
    }
}