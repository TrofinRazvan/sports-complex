package com.springboot.sportscomplex.integrationTesting;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.sportscomplex.models.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreateUserShouldPass() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .firstName("Alin")
                .lastName("Traian")
                .email("traian.alin@yahoo.com")
                .phoneNumber("0752145854")
                .dateOfBirth(LocalDate.parse("1998-06-21"))
                .gender("Male")
                .address("florilor nr.3")
                .city("Bucuresti")
                .emergencyContactName("Ioana")
                .emergencyContactPhoneNumber("0799254125")
                .build();
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToString(userDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(userDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userDTO.getLastName()))
                .andExpect(jsonPath("$.email").value(userDTO.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(userDTO.getPhoneNumber()))
                .andExpect(jsonPath("$.dateOfBirth").value(userDTO.getDateOfBirth()))
                .andExpect(jsonPath("$.gender").value(userDTO.getGender()))
                .andExpect(jsonPath("$.address").value(userDTO.getAddress()))
                .andExpect(jsonPath("$.city").value(userDTO.getCity()))
                .andExpect(jsonPath("$.emergencyContactName").value(userDTO.getEmergencyContactName()))
                .andExpect(jsonPath("$.emergencyContactPhoneNumber").value(userDTO.getEmergencyContactPhoneNumber()));
    }

    private String objectToString(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }
}