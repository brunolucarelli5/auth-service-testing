package com.testing.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RegisterUserDTO {

    // los atributos del modelo
    private String name;

    private String lastname;

    private String username;

    private String email;

    private String password;

    private String role;

    // los getters y setters se generan automáticamente con Lombok

}
