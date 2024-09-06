package com.testing.backend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class LoginUserEmailDTO {

    private String email;

    private String password;
}
