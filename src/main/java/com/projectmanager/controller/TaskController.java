package com.projectmanager.controller;

import com.projectmanager.model.dto.ResponseDto;
import com.projectmanager.model.dto.Status;
import com.projectmanager.model.dto.task.TaskCreateReqDto;
import com.projectmanager.model.dto.task.TaskResponseDto;
import com.projectmanager.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {

    private final String INCORRECT_SAVE_TASK_MESSAGE = "Saving project incorrectly";
    private final String INCORRECT_DELETE_TASK_MESSAGE = "Deleting task incorrectly";

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

    @DeleteMapping("/{id}")
    public ResponseDto deleteTaskById(@PathVariable @Min(1) Long id) {

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
