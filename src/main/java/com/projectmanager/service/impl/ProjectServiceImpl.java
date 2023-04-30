package com.projectmanager.service.impl;

import com.projectmanager.model.dto.ProjectRequestDto;
import com.projectmanager.model.dto.ProjectRequestUpdateDto;
import com.projectmanager.model.dto.ProjectResponseDto;
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
    public ProjectResponseDto saveProject(ProjectRequestDto projectRequestDto) {
        ProjectEntity sourceProjectEntity = projectMapper.toEntity(projectRequestDto);
        ProjectEntity savedProjectEntity = projectRepository.saveProject(sourceProjectEntity);
        return projectMapper.toResponseDto(savedProjectEntity);
    }

    @Override
    public ProjectResponseDto updateProject(ProjectRequestUpdateDto projectRequestUpdateDto) {

        projectRepository.findProjectById(projectRequestUpdateDto.getId())
                .orElseThrow(() -> new NotFoundProjectException(String.format(
                        NOT_FOUND_PROJECT_MESSAGE,
                        projectRequestUpdateDto.getId()))
                );

        ProjectEntity sourceEntity = projectMapper.toEntity(projectRequestUpdateDto);
        ProjectEntity updatedEntity = projectRepository.updateProject(sourceEntity);

        return projectMapper.toResponseDto(updatedEntity);
    }

    @Override
    public int deleteProject(long projectId) {

        projectRepository.findProjectById(projectId)
                .orElseThrow(() -> new NotFoundProjectException(String.format(NOT_FOUND_PROJECT_MESSAGE, projectId)));

        return projectRepository.deleteProject(projectId);
    }
}