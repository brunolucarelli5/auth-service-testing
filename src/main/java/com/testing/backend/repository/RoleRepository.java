package com.testing.backend.repository;

import com.testing.backend.dto.RoleDTO;
import com.testing.backend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    // se hace uso de los métodos de JpaRepository para utilidades básicas

    Optional<Role> findByName(String name);


    List<Role> findAll();

    void save(RoleDTO role);
}
