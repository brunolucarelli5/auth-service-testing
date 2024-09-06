package com.testing.backend.service;

import com.testing.backend.dto.LoginUserDTO;
import com.testing.backend.dto.LoginUserEmailDTO;
import com.testing.backend.dto.RegisterUserDTO;
import com.testing.backend.model.Role;
import com.testing.backend.model.User;
import com.testing.backend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    private final RoleService roleService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            RoleService roleService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDTO input) {

        // creo el rol antes de asignarlo al usuario
        Role role = this.roleService.findRoleByName(input.getRole());

        User user = new User()
                .setName(input.getName())
                .setLastname(input.getLastname())
                .setUsername(input.getUsername())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(role);

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }

    public User authenticateWithEmail(LoginUserEmailDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
