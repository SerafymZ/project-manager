package com.projectmanager.service;

import com.projectmanager.model.dto.ProjectRequestDto;
import com.projectmanager.model.dto.ProjectRequestUpdateDto;
import com.projectmanager.model.dto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {
    List<ProjectResponseDto> getAllProjects();
    ProjectResponseDto getProjectById(long id);
    ProjectResponseDto saveProject(ProjectRequestDto projectRequestDto);
    ProjectResponseDto updateProject(ProjectRequestUpdateDto projectRequestUpdateDto);
    int deleteProject(long projectId);
}