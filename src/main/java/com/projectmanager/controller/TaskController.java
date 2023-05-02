package com.projectmanager.controller;

import com.projectmanager.model.dto.ResponseDto;
import com.projectmanager.model.dto.Status;
import com.projectmanager.model.dto.task.TaskCreateReqDto;
import com.projectmanager.model.dto.task.TaskResponseDto;
import com.projectmanager.model.dto.task.TaskStatusUpdateDto;
import com.projectmanager.model.dto.task.TaskUpdateDto;
import com.projectmanager.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {

    private final String INCORRECT_SAVE_TASK_MESSAGE = "Saving project incorrectly";
    private final String INCORRECT_DELETE_TASK_MESSAGE = "Deleting task incorrectly";
    private final String INCORRECT_STATUS_UPDATE_TASK_MESSAGE = "Updating task status incorrectly";
    private final String INCORRECT_UPDATE_TASK_MESSAGE = "Updating task incorrectly";

    private final TaskService taskService;

    @GetMapping
    public List<TaskResponseDto> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public ResponseDto saveTask(@Valid @RequestBody TaskCreateReqDto reqDto) {

        var resultDto = new ResponseDto();

        int savedTasksCount = taskService.saveTask(reqDto);
        if(savedTasksCount == 1) {
            resultDto.setStatus(Status.OK);
        } else {
            resultDto.setStatus(Status.Failed);
            resultDto.setErrors(List.of(INCORRECT_SAVE_TASK_MESSAGE));
        }

        return resultDto;
    }

    @PutMapping("/status/{taskId}")
    public ResponseDto updateTaskStatus(@PathVariable @Min(1) Long taskId,
                                        @Valid @RequestBody TaskStatusUpdateDto updateDto) {

        updateDto.setTaskId(taskId);

        int updatedCount = taskService.updateTaskStatus(updateDto);

        var resultDto = new ResponseDto();
        if(updatedCount == 1) {
            resultDto.setStatus(Status.OK);
        } else {
            resultDto.setStatus(Status.Failed);
            resultDto.setErrors(List.of(INCORRECT_STATUS_UPDATE_TASK_MESSAGE));
        }

        return resultDto;
    }

    @PutMapping("/{id}")
    public ResponseDto updateTask(@Min(1) @PathVariable Long id, @Valid @RequestBody TaskUpdateDto taskUpdateDto) {

        taskUpdateDto.setId(id);

        var resultDto = new ResponseDto();

        int updatedTasksCount = taskService.updateTask(taskUpdateDto);

        if(updatedTasksCount == 1) {
            resultDto.setStatus(Status.OK);
        } else {
            resultDto.setStatus(Status.Failed);
            resultDto.setErrors(List.of(INCORRECT_UPDATE_TASK_MESSAGE));
        }

        return resultDto;
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteTaskById(@Min(1) @PathVariable Long id) throws AccessDeniedException {

        int deletedTasksCount = taskService.deleteTaskById(id);

        var resultDto = new ResponseDto();
        if(deletedTasksCount == 1) {
            resultDto.setStatus(Status.OK);
        } else {
            resultDto.setStatus(Status.Failed);
            resultDto.setErrors(List.of(INCORRECT_DELETE_TASK_MESSAGE));
        }

        return resultDto;
    }
}