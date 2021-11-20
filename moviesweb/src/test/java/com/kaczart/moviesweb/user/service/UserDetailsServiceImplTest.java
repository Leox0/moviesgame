package com.kaczart.moviesweb.user.service;

import com.kaczart.moviesweb.user.entitiy.UserEntity;
import com.kaczart.moviesweb.user.model.MyUserDetails;
import com.kaczart.moviesweb.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserDetailsServiceImplTest {


    @Test
    void shouldLoadUserDetailsByUsername() {
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        UserEntity userEntity = new UserEntity(1L,"test-user", "test-user-password", 0, false, null);
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(mockUserRepository);

        when(mockUserRepository.getUserByUsername(Mockito.anyString())).thenReturn(userEntity);

        assertEquals(userDetailsService.loadUserByUsername(userEntity.getUsername()), new MyUserDetails(userEntity));
    }

    @Test
    void shouldThrowException() {
        UserRepository mockUserRepository = Mockito.mock(UserRepository.class);
        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(mockUserRepository);

        when(mockUserRepository.getUserByUsername(Mockito.anyString())).thenReturn(null);

        Executable e = () -> userDetailsService.loadUserByUsername("test-user");

        assertThrows(UsernameNotFoundException.class, e);

    }

}