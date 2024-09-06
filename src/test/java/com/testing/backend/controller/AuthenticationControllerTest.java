package com.testing.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.backend.config.TestSecurityConfig;
import com.testing.backend.dto.LoginUserEmailDTO;
import com.testing.backend.model.LoginResponse;
import com.testing.backend.model.Role;
import com.testing.backend.model.User;
import com.testing.backend.service.AuthenticationService;
import com.testing.backend.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestSecurityConfig.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationService authenticationService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testAuthenticateWithEmail() throws Exception {
        LoginUserEmailDTO dto = new LoginUserEmailDTO();
        dto.setEmail("john.doe@example.com");
        dto.setPassword("password123");

        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setLastname("Doe"); // Set the lastname property
        Role role = new Role();
        role.setName("USER");
        user.setRole(role);

        String token = "jwt_token";
        long expirationTime = 3600L; // tiempo de expiración en segundos

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setExpiresIn(expirationTime);
        response.setUsername("john_doe");
        response.setRole("USER");

        // Configura los mocks para los métodos del servicio
        when(authenticationService.authenticateWithEmail(any(LoginUserEmailDTO.class))).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(token);
        when(jwtService.getExpirationTime()).thenReturn(expirationTime);

        // Ejecuta la solicitud y verifica la respuesta
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(token))
                .andExpect(jsonPath("$.expiresIn").value(expirationTime))
                .andExpect(jsonPath("$.username").value("john_doe"))
                .andExpect(jsonPath("$.role").value("USER"));
    }
}