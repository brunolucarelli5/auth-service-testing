package com.testing.backend.controller;
import com.testing.backend.dto.RoleDTO;
import com.testing.backend.model.Role;
import com.testing.backend.model.User;
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
    public ResponseEntity<List<Role>> allRoles() {
        List<Role> roles = roleService.allRoles();

        return ResponseEntity.ok(roles);
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
