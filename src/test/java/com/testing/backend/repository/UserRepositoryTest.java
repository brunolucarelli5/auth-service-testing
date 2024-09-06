package com.testing.backend.repository;

import com.testing.backend.model.Role;
import com.testing.backend.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Role role = new Role();
        role.setName("USER");
        entityManager.persist(role);
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        user.setName("John");
        user.setLastname("Doe");
        user.setPassword("password123");
        user.setRole(role);
        entityManager.persist(user);
        entityManager.flush();
    }

    @Test
    void testFindByUsername() {
        Optional<User> foundUser = userRepository.findByUsername("john_doe");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("john_doe");
    }

    @Test
    void testFindByEmail() {
        Optional<User> foundUser = userRepository.findByEmail("john.doe@example.com");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    void testFindByUsernameNotFound() {
        Optional<User> foundUser = userRepository.findByUsername("non_existent_user");
        assertThat(foundUser).isNotPresent();
    }

    @Test
    void testFindByEmailNotFound() {
        Optional<User> foundUser = userRepository.findByEmail("non_existent@example.com");
        assertThat(foundUser).isNotPresent();
    }
}