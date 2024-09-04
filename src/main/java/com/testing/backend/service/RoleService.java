package com.testing.backend.service;
import com.testing.backend.dto.RoleDTO;
import com.testing.backend.model.Role;
import com.testing.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> allRoles() {
        return new ArrayList<>(roleRepository.findAll());
    }

    public Role createRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        roleRepository.save(role);
        return role;
    }

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() ->
                new RuntimeException("Role not found with name: " + name));
    }

    public void asignRoleToUser(String username, String roleName) {
        Role role = roleRepository.findByName(roleName).orElseThrow(() ->
                new RuntimeException("Role not found with name: " + roleName));

    }
}
