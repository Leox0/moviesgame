package com.kaczart.moviesweb.user.service;

import com.kaczart.moviesweb.user.entitiy.UserRoleEntity;
import com.kaczart.moviesweb.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    private final UserRoleRepository userRoleRepo;

    public void buildAdminRole() {
        userRoleRepo.save(UserRoleEntity.builder().name("ADMIN").build());
    }

    public void buildUserRole() {
        userRoleRepo.save(UserRoleEntity.builder().name("USER").build());
    }

    public UserRoleEntity getUserAuthority() throws RuntimeException {
        return userRoleRepo.findByName("USER").orElseThrow(() -> new RuntimeException("ROLE NOT FOUND"));
    }

    public UserRoleEntity getAdminAuthority() throws RuntimeException {
        return userRoleRepo.findByName("ADMIN").orElseThrow(() -> new RuntimeException("ROLE NOT FOUND"));
    }
}
