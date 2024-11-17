package com.testing.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.testing.backend.config.TestSecurityConfig;
import com.testing.backend.dto.LoginUserEmailDTO;
import com.testing.backend.model.Role;
import com.testing.backend.model.User;
import com.testing.backend.service.AuthenticationService;
import com.testing.backend.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
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
@ActiveProfiles("test")
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthenticationService authenticationService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String EMAIL = "john.doe@example.com";
    private static final String PASSWORD = "password123";
    private static final String TOKEN = "jwt_token";
    private static final long EXPIRATION_TIME = 3600L;
    private static final String NAME = "John";
    private static final String ROLE_NAME = "USER";

    private LoginUserEmailDTO dto;
    private User user;

    @BeforeEach
    void setUp() {
        dto = new LoginUserEmailDTO();
        dto.setEmail(EMAIL);
        dto.setPassword(PASSWORD);

        user = new User();
        user.setUsername("john_doe");
        user.setEmail(EMAIL);
        user.setName(NAME);
        user.setLastname("Doe");
        Role role = new Role();
        role.setName(ROLE_NAME);
        user.setRole(role);
    }

    @Test
    void testAuthenticateWithEmail() throws Exception {
        // Configura los mocks para los métodos del servicio
        when(authenticationService.authenticateWithEmail(any(LoginUserEmailDTO.class))).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(TOKEN);
        when(jwtService.getExpirationTime()).thenReturn(EXPIRATION_TIME);

        // Ejecuta la solicitud y verifica la respuesta
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value(TOKEN))
                .andExpect(jsonPath("$.expiresIn").value(EXPIRATION_TIME))
                .andExpect(jsonPath("$.name").value(NAME))
                .andExpect(jsonPath("$.role").value(ROLE_NAME));
    }
}