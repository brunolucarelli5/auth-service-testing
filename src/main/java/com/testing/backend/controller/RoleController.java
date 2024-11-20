package com.testing.backend.controller;
import com.testing.backend.dto.RoleDTO;
import com.testing.backend.model.Role;
import com.testing.backend.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/roles")
@RestController
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> allRoles() {
        Map<String, Object> response = new HashMap<>();
        List<Role> roles = roleService.allRoles();
        int rolesCount = roles.size();
        String rolesCountString = Integer.toString(rolesCount);
        response.put("roles", roles);
        response.put("message",  rolesCountString + " roles retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> createRole(@RequestBody RoleDTO role) {
        Role createdRole = roleService.createRole(role);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Role created successfully");
        response.put("role", createdRole);
        return ResponseEntity.ok(response);
    }

}
