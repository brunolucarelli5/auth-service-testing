package com.testing.backend.service;

import com.testing.backend.dto.LoginUserDTO;
import com.testing.backend.dto.LoginUserEmailDTO;
import com.testing.backend.dto.RegisterUserDTO;
import com.testing.backend.model.Role;
import com.testing.backend.model.User;
import com.testing.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
public class AuthenticationServiceTest {

    @Mock
    private RoleService roleService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSignup() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setName("John");
        dto.setLastname("Doe");
        dto.setUsername("john_doe");
        dto.setEmail("john.doe@example.com");
        dto.setPassword("password123");
        dto.setRole("USER");

        Role role = new Role();
        role.setName("USER");

        User user = new User();
        user.setName("John");
        user.setLastname("Doe");
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("encoded_password");
        user.setRole(role);

        when(roleService.findRoleByName("USER")).thenReturn(role);
        when(passwordEncoder.encode("password123")).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = authenticationService.signup(dto);

        assertEquals("john_doe", savedUser.getUsername());
        verify(roleService).findRoleByName("USER");
        verify(passwordEncoder).encode("password123");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testAuthenticate() {
        LoginUserDTO dto = new LoginUserDTO();
        dto.setUsername("john_doe");
        dto.setPassword("password123");

        User user = new User();
        user.setUsername("john_doe");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);  // Simulamos que la autenticación es exitosa
        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(user));

        User authenticatedUser = authenticationService.authenticate(dto);

        assertEquals("john_doe", authenticatedUser.getUsername());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByUsername("john_doe");
    }

    @Test
    void testAuthenticateWithEmail() {
        LoginUserEmailDTO dto = new LoginUserEmailDTO();
        dto.setEmail("john.doe@example.com");
        dto.setPassword("password123");

        User user = new User();
        user.setEmail("john.doe@example.com");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);  // Simulamos que la autenticación es exitosa
        when(userRepository.findByEmail("john.doe@example.com")).thenReturn(Optional.of(user));

        User authenticatedUser = authenticationService.authenticateWithEmail(dto);

        assertEquals("john.doe@example.com", authenticatedUser.getEmail());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepository).findByEmail("john.doe@example.com");
    }
}
