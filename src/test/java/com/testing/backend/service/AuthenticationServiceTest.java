package com.testing.backend.service;

import com.testing.backend.dto.LoginUserEmailDTO;
import com.testing.backend.model.User;
import com.testing.backend.repository.UserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.AuthenticationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService authService;

    @Test
    public void authenticate_validCredentials_returnsJwt() {
        // Configuración
        String email = "brunolucarelli5@gmail.com";
        String password = "a";
        LoginUserEmailDTO dtoLogin = new LoginUserEmailDTO(email, password);
        User mockUser = new User(username, passwordEncoder.encode(password));
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        // Ejecución
        String token = authService.authenticateWithEmail(dtoLogin);

        // Verificación
        assertNotNull(token);
        assertTrue(token.startsWith("Bearer "));
    }

    @Test(expected = AuthenticationException.class)
    public void authenticate_invalidCredentials_throwsException() {
        // Configuración
        String username = "user";
        String password = "wrongpassword";
        User mockUser = new User(username, passwordEncoder.encode("password"));
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        // Ejecución
        authService.authenticate(username, password);

        // Verificación se realiza con la excepción
    }
}
