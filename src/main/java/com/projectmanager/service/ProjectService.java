package com.projectmanager.service;

import com.projectmanager.model.dto.ProjectRequestDto;
import com.projectmanager.model.dto.ProjectRequestUpdateDto;
import com.projectmanager.model.dto.ProjectResponseDto;

import java.util.List;

public interface ProjectService {
    List<ProjectResponseDto> getAllProjects();
    ProjectResponseDto getProjectById(long id);
    int saveProject(ProjectRequestDto projectRequestDto);
    int updateProject(ProjectRequestUpdateDto projectRequestUpdateDto);
    int deleteProject(long projectId);
}