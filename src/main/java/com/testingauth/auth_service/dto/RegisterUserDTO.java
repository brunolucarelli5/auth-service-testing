package com.testingauth.auth_service.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserDTO {

    // los atributos del modelo
    private String name;

    private String lastname;

    private String username;

    private String email;

    private String password;

    // los getters y setters se generan automáticamente con Lombok

}
