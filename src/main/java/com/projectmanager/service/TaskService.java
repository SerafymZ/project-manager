package com.projectmanager.service;

import com.projectmanager.model.dto.task.TaskCreateReqDto;
import com.projectmanager.model.dto.task.TaskResponseDto;

import java.util.List;

public interface TaskService {

    List<TaskResponseDto> getAllTasks();
    int saveTask(TaskCreateReqDto reqDto);
    void deleteTasksByProjectID(long projectId);
}
