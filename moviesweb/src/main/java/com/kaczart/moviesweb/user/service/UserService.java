package com.kaczart.moviesweb.user.service;

import com.kaczart.moviesweb.user.entitiy.UserEntity;
import com.kaczart.moviesweb.user.entitiy.UserRoleEntity;
import com.kaczart.moviesweb.user.exceptions.ExceptionReason;
import com.kaczart.moviesweb.user.exceptions.UserException;
import com.kaczart.moviesweb.user.model.RequestUser;
import com.kaczart.moviesweb.user.model.ResponseUser;
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

    public String createUser(RequestUser requestUser) {
        return createAccount(requestUser, false);
    }

    public String createAdmin(RequestUser requestUser) {
        return createAccount(requestUser, true);
    }

    public ResponseUser getUserByName(String name){
        UserEntity userEntity = repo.getUserByUsername(name);
        return buildResponseUser(userEntity);
    }

    public ResponseUser addPoints(ResponseUser user, int points) {
        UserEntity userEntity = repo.getUserByUsername(user.getUsername());
        userEntity.setPoints(userEntity.getPoints() + points);
        repo.save(userEntity);
        return buildResponseUser(userEntity);
    }

    private String createAccount(RequestUser requestUser, boolean b) {
        UserEntity userEntity = buildUserEntity(requestUser, b);
        repo.save(userEntity);
        return String.format(ACCOUNT_CREATION_OUTPUT, userEntity.getUsername());
    }

    private UserEntity buildUserEntity(RequestUser requestUser, boolean isAdmin) throws UserException {
        UserRoleEntity userRole = ((isAdmin) ? userRoleService.getAdminAuthority() : userRoleService.getUserAuthority());
        if (!isUsernameUsed(requestUser.getUsername())) {
            return buildUserEntity(requestUser, isAdmin, userRole);
        } else {
            throw new UserException(ExceptionReason.USER_ALREADY_EXISTS);
        }
    }

    protected UserEntity buildUserEntity(RequestUser requestUser, boolean isAdmin, UserRoleEntity userRole) {
        return UserEntity.builder()
                .username(requestUser.getUsername())
                .password(passwordEncoder.encode(requestUser.getPassword()))
                .roles(Set.of(userRole))
                .enabled(isAdmin)
                .points(0)
                .build();
    }

    private boolean isUsernameUsed(String username) {
        return repo.existsByUsername(username);
    }

    public ResponseUser buildResponseUser(UserEntity user) {
        return new ResponseUser(user.getUsername(), user.getPoints());
    }
}
