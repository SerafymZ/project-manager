package com.projectmanager.repository;

import com.projectmanager.model.entity.TaskEntity;

import java.util.List;

public interface TaskRepository {

    List<TaskEntity> findAllTasks();
    int deleteTasksByProjectID(long projectId);
}
