package com.projectmanager.repository;

import com.projectmanager.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    Optional<UserEntity> findByName(String username);

    Optional<UserEntity> findById(Long userId);
}
