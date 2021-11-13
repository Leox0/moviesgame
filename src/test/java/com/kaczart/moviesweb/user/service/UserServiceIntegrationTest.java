package com.kaczart.moviesweb.user.service;

import com.kaczart.moviesweb.user.entitiy.UserEntity;
import com.kaczart.moviesweb.user.exceptions.UserException;
import com.kaczart.moviesweb.user.model.RequestUser;
import com.kaczart.moviesweb.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserServiceIntegrationTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    void givenUserShouldReturnUserName() {
        UserEntity user = UserEntity.builder().username("test-user").password("test-user-password").enabled(false).build();
        RequestUser requestUser = new RequestUser("test-user", "test-user-password");

        userService.createUser(requestUser);

        assertEquals(userRepository.getUserByUsername(user.getUsername()).getUsername(), requestUser.getUsername());

    }

    @Test
    void shouldThrowExceptionWhenUsernameWasUsedBefore() {
        RequestUser requestUser = new RequestUser("test-user", "test-user-password");
        RequestUser requestUserDuplicateName = new RequestUser("test-user", "test-user-password2");

        userService.createUser(requestUser);

        Executable e = () -> userService.createUser(requestUserDuplicateName);

        assertThrows(UserException.class, e);

    }

    @Test
    void shouldCreateUserWithUserPermission(){
        RequestUser requestUser = new RequestUser("test-user", "test-user-password");

        userService.createUser(requestUser);

        assertTrue(userRepository.getUserByUsername(requestUser.getUsername()).getRoles().stream().anyMatch(e -> e.getName().equals("USER")));
        assertFalse(userRepository.getUserByUsername(requestUser.getUsername()).getRoles().stream().anyMatch(e -> e.getName().equals("ADMIN")));

    }

    @Test
    void shouldCreateUserWithAdminPermission() {
        RequestUser requestUser = new RequestUser("test-user", "test-user-password");

        userService.createAdmin(requestUser);

        assertTrue(userRepository.getUserByUsername(requestUser.getUsername()).getRoles().stream().anyMatch(e -> e.getName().equals("ADMIN")));
        assertFalse(userRepository.getUserByUsername(requestUser.getUsername()).getRoles().stream().anyMatch(e -> e.getName().equals("USER")));

    }
}