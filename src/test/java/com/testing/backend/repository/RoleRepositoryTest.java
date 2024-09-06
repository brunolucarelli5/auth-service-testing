package com.testing.backend.repository;

import com.testing.backend.dto.RoleDTO;
import com.testing.backend.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RoleRepositoryTest {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        roleRepository.deleteAll();
    }

    @Test
    public void testFindByName() {
        // Arrange
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("ADMIN");

        // Mapear RoleDTO a Role manualmente en la prueba
        Role role = new Role();
        role.setName(roleDTO.getName());

        roleRepository.save(role);

        // Act
        Optional<Role> foundRole = roleRepository.findByName("ADMIN");

        // Assert
        assertThat(foundRole).isPresent();
        assertThat(foundRole.get().getName()).isEqualTo("ADMIN");
    }

    @Test
    public void testFindAll() {
        // Arrange
        RoleDTO roleDTO1 = new RoleDTO();
        roleDTO1.setName("USER");

        Role role1 = new Role();
        role1.setName(roleDTO1.getName());

        roleRepository.save(role1);

        RoleDTO roleDTO2 = new RoleDTO();
        roleDTO2.setName("ADMIN");

        Role role2 = new Role();
        role2.setName(roleDTO2.getName());

        roleRepository.save(role2);

        // Act
        List<Role> roles = roleRepository.findAll();

        // Assert
        assertThat(roles).hasSize(2);
        assertThat(roles).extracting(Role::getName).containsExactlyInAnyOrder("USER", "ADMIN");
    }

    @Test
    public void testSave() {
        // Arrange
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName("MODERATOR");

        // Mapear RoleDTO a Role manualmente en la prueba
        Role role = new Role();
        role.setName(roleDTO.getName());

        // Act
        Role savedRole = roleRepository.save(role);

        // Assert
        assertThat(savedRole).isNotNull();
        assertThat(savedRole.getId()).isNotNull();
        assertThat(savedRole.getName()).isEqualTo("MODERATOR");
    }
}
