package com.testing.backend.controller;

import com.testing.backend.dto.LoginUserDTO;
import com.testing.backend.dto.LoginUserEmailDTO;
import com.testing.backend.dto.RegisterUserDTO;
import com.testing.backend.model.LoginResponse;
import com.testing.backend.model.User;
import com.testing.backend.service.AuthenticationService;
import com.testing.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUserDTO registerUserDTO) {
        User registeredUser = authenticationService.signup(registerUserDTO);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateWithEmail(@RequestBody LoginUserEmailDTO loginUserEmailDTO) {
        User authenticatedUser = authenticationService.authenticateWithEmail(loginUserEmailDTO);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        String name = authenticatedUser.getName();

        String role = authenticatedUser.getRole().getName();

        LoginResponse loginResponse = new LoginResponse()
                .setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime())
                .setName(name)
                .setRole(role)
                .setSecret_word("banana");

        return ResponseEntity.ok(loginResponse);
    }
}
