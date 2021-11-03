package com.kaczart.moviesweb.user.repository;

import com.kaczart.moviesweb.user.entitiy.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByUsername(String username);

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    UserEntity getUserByUsername(@Param("username") String username);

}
