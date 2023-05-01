package com.projectmanager.service.impl;

import com.projectmanager.exception.NotFoundTaskException;
import com.projectmanager.model.dto.task.TaskCreateReqDto;
import com.projectmanager.model.dto.task.TaskResponseDto;
import com.projectmanager.model.dto.task.TaskStatusUpdateDto;
import com.projectmanager.model.dto.task.TaskUpdateDto;
import com.projectmanager.model.entity.TaskCreateEntity;
import com.projectmanager.model.entity.TaskEntity;
import com.projectmanager.model.mapper.TaskMapper;
import com.projectmanager.repository.TaskRepository;
import com.projectmanager.service.TaskService;
import com.projectmanager.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final String TASK_NOT_FOUND_MESSAGE = "There is no project with ID = %d in database.";

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final ValidationService validationService;

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
        validationService.validate(reqDto);
        TaskCreateEntity sourceEntity = taskMapper.toCreateEntity(reqDto);
        return taskRepository.saveTask(sourceEntity);
    }

    @Override
    public void deleteTasksByProjectID(long projectId) {
        taskRepository.deleteTasksByProjectId(projectId);
    }

    @Override
    public int deleteTaskById(Long id) {

        taskRepository.findTaskById(id)
                .orElseThrow(() -> new NotFoundTaskException(String.format(TASK_NOT_FOUND_MESSAGE, id)));

        return taskRepository.deleteTaskById(id);
    }

    @Override
    public int updateTaskStatus(TaskStatusUpdateDto updateDto) {

        taskRepository.findTaskById(updateDto.getTaskId())
                .orElseThrow(() -> new NotFoundTaskException(String.format(TASK_NOT_FOUND_MESSAGE, updateDto.getTaskId())));

        return taskRepository.updateTaskStatus(updateDto.getTaskId(), updateDto.getTaskStatusId());
    }

    @Override
    public int updateTask(TaskUpdateDto updateDto) {

        validationService.validate(updateDto);
        taskRepository.findTaskById(updateDto.getId())
                .orElseThrow(() -> new NotFoundTaskException(String.format(TASK_NOT_FOUND_MESSAGE, updateDto.getId())));

        TaskEntity sourceEntity = taskMapper.toEntity(updateDto);
        return taskRepository.updateTask(sourceEntity);
    }
}