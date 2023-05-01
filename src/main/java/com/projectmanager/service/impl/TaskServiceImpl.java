package com.projectmanager.service.impl;

import com.projectmanager.model.dto.task.TaskCreateReqDto;
import com.projectmanager.model.dto.task.TaskResponseDto;
import com.projectmanager.model.entity.TaskEntity;
import com.projectmanager.model.mapper.TaskMapper;
import com.projectmanager.repository.TaskRepository;
import com.projectmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskResponseDto> getAllTasks() {

        List<TaskEntity> entityList = taskRepository.findAllTasks();

        if (entityList.isEmpty()) {
            return Collections.emptyList();
        } else {
            return entityList.stream()
                    .map(taskMapper::toResponseDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public int saveTask(TaskCreateReqDto reqDto) {
        return 0;
    }

    @Override
    public void deleteTasksByProjectID(long projectId) {
        taskRepository.deleteTasksByProjectID(projectId);
    }

}
