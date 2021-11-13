package com.kaczart.moviesweb.user.service;

import com.kaczart.moviesweb.user.exceptions.UserException;
import com.kaczart.moviesweb.user.model.RequestUser;
import com.kaczart.moviesweb.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserRoleService userRoleService;

    @Test
    void shouldThrowException() {
        RequestUser requestUser = new RequestUser("test-user", "test-user-password");
        UserService userService = new UserService(userRepository, userRoleService, passwordEncoder);

        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        Executable e = () -> userService.createUser(requestUser);

        assertThrows(UserException.class, e);

    }

//    @Test
//    void shouldNotThrowException() {
//        RequestUser requestUser = new RequestUser("test-user", "test-user-password");
//        UserService userService = new UserService(userRepository, userRoleService, passwordEncoder);
////        UserService userServiceSpy = Mockito.spy(userService);
//
//        when(userRepository.existsByUsername(anyString())).thenReturn(false);
////        when(userService.buildUserEntity(requestUser, false, new UserRoleEntity())).thenReturn(new UserEntity(1L, requestUser.getUsername(), requestUser.getPassword(), 0, false, null));
//        doReturn(new UserEntity(1L, requestUser.getUsername(), requestUser.getPassword(), 0, false, null))
//                .when(spy(userService).buildUserEntity(requestUser, false, new UserRoleEntity()));
//        Executable e = () -> userService.createUser(requestUser);
//
//        assertDoesNotThrow(e);
//
//    }

}