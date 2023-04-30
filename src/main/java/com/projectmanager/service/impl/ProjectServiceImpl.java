package com.projectmanager.service.impl;

import com.projectmanager.model.dto.project.ProjectRequestDto;
import com.projectmanager.model.dto.project.ProjectRequestUpdateDto;
import com.projectmanager.model.dto.project.ProjectResponseDto;
import com.projectmanager.model.entity.ProjectEntity;
import com.projectmanager.exception.NotFoundProjectException;
import com.projectmanager.model.mapper.ProjectMapper;
import com.projectmanager.repository.ProjectRepository;
import com.projectmanager.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private static final String NOT_FOUND_PROJECT_MESSAGE = "There is no project with ID = %d in database.";

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public List<ProjectResponseDto> getAllProjects() {
        List<ProjectEntity> entityList = projectRepository.findAllProjects();

        if (entityList.isEmpty()) {
            return Collections.emptyList();
        } else {
            return entityList.stream()
                    .map(projectMapper::toResponseDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public ProjectResponseDto getProjectById(long id) {

        ProjectEntity projectEntity = projectRepository.findProjectById(id)
                .orElseThrow(() -> new NotFoundProjectException(String.format(NOT_FOUND_PROJECT_MESSAGE, id)));

        return projectMapper.toResponseDto(projectEntity);
    }

    @Override
    public int saveProject(ProjectRequestDto projectRequestDto) {
        ProjectEntity sourceProjectEntity = projectMapper.toEntity(projectRequestDto);
        return projectRepository.saveProject(sourceProjectEntity);
    }

    @Override
    public int updateProject(ProjectRequestUpdateDto projectRequestUpdateDto) {

        projectRepository.findProjectById(projectRequestUpdateDto.getId())
                .orElseThrow(() -> new NotFoundProjectException(String.format(
                        NOT_FOUND_PROJECT_MESSAGE,
                        projectRequestUpdateDto.getId()))
                );

        ProjectEntity sourceEntity = projectMapper.toEntity(projectRequestUpdateDto);
        return projectRepository.updateProject(sourceEntity);
    }

    @Override
    public int deleteProject(long projectId) {

        projectRepository.findProjectById(projectId)
                .orElseThrow(() -> new NotFoundProjectException(String.format(NOT_FOUND_PROJECT_MESSAGE, projectId)));

        return projectRepository.deleteProject(projectId);
    }
}