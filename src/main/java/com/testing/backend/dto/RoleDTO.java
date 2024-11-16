package com.testing.backend.dto;
import lombok.Data;

@Data
public class RoleDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
