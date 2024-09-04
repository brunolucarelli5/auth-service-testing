/*
package com.testing.backend.repository;

import com.testing.backend.model.User;
import com.testing.backend.service.AuthenticationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.naming.AuthenticationException;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class RoleRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthenticationService authService;

    @Test
    public void authenticate_validCredentials_returnsJwt() {
        // Configuración
        String username = "user";
        String password = "password";
        User mockUser = new User(username, passwordEncoder.encode(password));
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(mockUser));

        // Ejecución
        String token = authService.authenticateWithEmail(username, password);

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
*/
