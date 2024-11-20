package com.testing.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class LoginResponse {

    private String token;
    private long expiresIn;
    private String name;
    private String role;
    private String secretWord;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public LoginResponse setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    public String getName() {
        return name;
    }

    public LoginResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getRole() {
        return role;
    }

    public LoginResponse setRole(String role) {
        this.role = role;
        return this;
    }

    @JsonProperty("secretWord")
    public String getSecretWord() {
        return this.secretWord;
    }

    public LoginResponse setSecretWord(String secret_word) {
        this.secretWord = secret_word;
        return this;
    }
}