package com.linh.hotelbk.repository;

import com.linh.hotelbk.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByEmail(String email);
    UserEntity findByEmail(String email);
    UserEntity findByResetPassToken(String token);
}
