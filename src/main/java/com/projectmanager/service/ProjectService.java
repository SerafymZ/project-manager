package com.projectmanager.service;

import com.projectmanager.model.dto.project.ProjectRequestDto;
import com.projectmanager.model.dto.project.ProjectRequestUpdateDto;
import com.projectmanager.model.dto.project.ProjectResponseDto;

import java.util.List;

public interface ProjectService {
    List<ProjectResponseDto> getAllProjects();
    ProjectResponseDto getProjectById(long id);
    int saveProject(ProjectRequestDto projectRequestDto);
    int updateProject(ProjectRequestUpdateDto projectRequestUpdateDto);
    int deleteProject(long projectId);
}