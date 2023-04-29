package com.projectmanager.controller;

import com.projectmanager.model.dto.*;
import com.projectmanager.service.ProjectService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    private final String INCORRECT_DELETE_PROJECT_MESSAGE = "Deleting project incorrectly";

    private final ProjectService projectService;

    @GetMapping
    public List<ProjectResponseDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @PostMapping
    public ProjectResponseDto saveProject(@Valid @RequestBody ProjectRequestDto projectRequestDto) {
        return projectService.saveProject(projectRequestDto);
    }

    @PutMapping("/{id}")
    public ProjectResponseDto updateProject(@PathVariable @Min(1) Long id,
        @Valid @RequestBody ProjectRequestUpdateDto dto) {

        dto.setId(id);
        return projectService.updateProject(dto);
    }

    @DeleteMapping("/{id}")
    public DeleteProjectResponseDto deleteProject(@PathVariable @Min(1) Long id) {

        int deletedProjectsCount = projectService.deleteProject(id);

        var resultDto = new DeleteProjectResponseDto();
        if(deletedProjectsCount == 1) {
            resultDto.setStatus(Status.OK);
        } else {
            resultDto.setStatus(Status.Failed);
            resultDto.setMessage(INCORRECT_DELETE_PROJECT_MESSAGE);
        }

        return resultDto;
    }
}