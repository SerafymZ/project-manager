package com.projectmanager.repository;

import com.projectmanager.model.entity.TaskEntity;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<TaskEntity> findAllTasks();
    Optional<TaskEntity> findTaskById(long id);
    int deleteTasksByProjectId(long projectId);
    int deleteTaskById(Long id);
    int updateTaskStatus(Long taskId, Long statusId);
}
