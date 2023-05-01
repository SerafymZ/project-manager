package com.projectmanager.service;

import com.projectmanager.model.dto.task.TaskCreateReqDto;
import com.projectmanager.model.dto.task.TaskResponseDto;
import com.projectmanager.model.dto.task.TaskStatusUpdateDto;
import com.projectmanager.model.dto.task.TaskUpdateDto;

import java.util.List;

public interface TaskService {

    List<TaskResponseDto> getAllTasks();
    int saveTask(TaskCreateReqDto reqDto);
    void deleteTasksByProjectID(long projectId);
    int deleteTaskById(Long id);
    int updateTaskStatus(TaskStatusUpdateDto updateDto);
    int updateTask(TaskUpdateDto taskUpdateDto);
}