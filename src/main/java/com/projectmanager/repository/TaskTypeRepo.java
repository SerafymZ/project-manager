package com.projectmanager.repository;

import com.projectmanager.model.entity.TaskTypeEntity;

import java.util.Optional;

public interface TaskTypeRepo {

    Optional<TaskTypeEntity> findTaskTypeById(Long typeId);
}