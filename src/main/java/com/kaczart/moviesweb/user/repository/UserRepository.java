package com.kaczart.moviesweb.user.repository;

import com.kaczart.moviesweb.user.entitiy.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);

    UserEntity getUserByUsername(String username);

}
