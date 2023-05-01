package com.projectmanager.repository;

import com.projectmanager.model.entity.TaskStatusEntity;

import java.util.Optional;

public interface TaskStatusRepository {

    Optional<TaskStatusEntity> findTaskStatusById(Long statusId);
}