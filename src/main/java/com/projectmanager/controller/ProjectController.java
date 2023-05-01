package com.projectmanager.controller;

import com.projectmanager.model.dto.*;
import com.projectmanager.model.dto.project.ProjectRequestDto;
import com.projectmanager.model.dto.project.ProjectRequestUpdateDto;
import com.projectmanager.model.dto.project.ProjectResponseDto;
import com.projectmanager.service.ProjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    private final String INCORRECT_SAVE_PROJECT_MESSAGE = "Saving project incorrectly";
    private final String INCORRECT_UPDATE_PROJECT_MESSAGE = "Updating project incorrectly";
    private final String INCORRECT_DELETE_PROJECT_MESSAGE = "Deleting project incorrectly";

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectResponseDto> getAllProjects() {

        return projectService.getAllProjects();
    }

    @PostMapping
    public ResponseDto saveProject(@Valid @RequestBody ProjectRequestDto projectRequestDto) {

        var resultDto = new ResponseDto();

        int savedProjectCount = projectService.saveProject(projectRequestDto);
        if(savedProjectCount == 1) {
            resultDto.setStatus(Status.OK);
        } else {
            resultDto.setStatus(Status.Failed);
            resultDto.setErrors(List.of(INCORRECT_SAVE_PROJECT_MESSAGE));
        }

        return resultDto;
    }

    @PutMapping("/{id}")
    public ResponseDto updateProject(@PathVariable @Min(1) Long id,
        @Valid @RequestBody ProjectRequestUpdateDto dto) {

        dto.setId(id);
        var resultDto = new ResponseDto();

        int updatedProjectCount = projectService.updateProject(dto);
        if(updatedProjectCount == 1) {
            resultDto.setStatus(Status.OK);
        } else {
            resultDto.setStatus(Status.Failed);
            resultDto.setErrors(List.of(INCORRECT_UPDATE_PROJECT_MESSAGE));
        }

        return resultDto;
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteProject(@PathVariable @Min(1) Long id) {

        int deletedProjectsCount = projectService.deleteProject(id);

        var resultDto = new ResponseDto();
        if(deletedProjectsCount > 1) {
            resultDto.setStatus(Status.OK);
        } else {
            resultDto.setStatus(Status.Failed);
            resultDto.setErrors(List.of(INCORRECT_DELETE_PROJECT_MESSAGE));
        }

        return resultDto;
    }
}