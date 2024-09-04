package com.testing.backend.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class LoginResponse {
    private String token;

    private long expiresIn;

    private String username;

    private String role;

}
