package com.projectmanager.service.impl;

import com.projectmanager.model.dto.ProjectRequestDto;
import com.projectmanager.model.dto.ProjectResponseDto;
import com.projectmanager.model.entity.ProjectEntity;
import com.projectmanager.model.exception.NotFoundProjectException;
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
        return null;
    }

    @Override
    public ProjectResponseDto updateProject(ProjectRequestDto projectRequestDto) {
        return null;
    }

    @Override
    public int deleteProjectById(int projectId) {
        return 0;
    }
}
