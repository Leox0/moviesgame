package com.kaczart.moviesweb.user.repository;

import com.kaczart.moviesweb.user.entitiy.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    Optional<UserRoleEntity> findByName(String name);

}
