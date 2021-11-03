package com.kaczart.moviesweb.user.service;

import com.kaczart.moviesweb.user.entitiy.UserEntity;
import com.kaczart.moviesweb.user.entitiy.UserRoleEntity;
import com.kaczart.moviesweb.user.model.RequestUser;
import com.kaczart.moviesweb.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final String ACCOUNT_CREATION_OUTPUT = "USERNAME: %s HAS BEEN CREATED";

    private final UserRepository repo;
    private final UserRoleService userRoleService;
    private final PasswordEncoder passwordEncoder;

    public String createUser(RequestUser requestUser) throws Exception {
        UserEntity userEntity = createAccount(requestUser, false);
        return String.format(ACCOUNT_CREATION_OUTPUT, userEntity.getUsername());
    }

    public String createAdmin(RequestUser requestUser) throws Exception {
        UserEntity userEntity = createAccount(requestUser, true);
        return String.format(ACCOUNT_CREATION_OUTPUT, userEntity.getUsername());
    }

    private UserEntity createAccount(RequestUser requestUser, boolean b) throws Exception {
        UserEntity userEntity = buildUserEntity(requestUser, b);
        repo.save(userEntity);
        return userEntity;
    }

    private UserEntity buildUserEntity(RequestUser requestUser, boolean isAdmin) throws Exception {
        UserRoleEntity userRole = ((isAdmin) ? userRoleService.getAdminAuthority() : userRoleService.getUserAuthority());
        if (!isUsernameUsed(requestUser.getUsername())) {
            return UserEntity.builder()
                    .username(requestUser.getUsername())
                    .password(passwordEncoder.encode(requestUser.getPassword()))
                    .roles(Set.of(userRole))
                    .enabled(isAdmin)
                    .points(0)
                    .build();
        } else {
            throw new Exception();
        }
    }

    private boolean isUsernameUsed(String username) {
        return repo.existsByUsername(username);
    }
}
