package com.projectmanager.service.impl;

import com.projectmanager.exception.NotFoundProjectException;
import com.projectmanager.model.dto.project.ProjectRequestDto;
import com.projectmanager.model.dto.project.ProjectRequestUpdateDto;
import com.projectmanager.model.dto.project.ProjectResponseDto;
import com.projectmanager.model.entity.ProjectEntity;
import com.projectmanager.model.mapper.ProjectMapper;
import com.projectmanager.repository.ProjectRepository;
import com.projectmanager.service.ProjectService;
import com.projectmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProjectServiceImpl implements ProjectService {

    private static final String NOT_FOUND_PROJECT_MESSAGE = "There is no project with ID = %d in database.";

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final TaskService taskService;

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
    @Transactional
    public int deleteProject(long projectId) {

        projectRepository.findProjectById(projectId)
                .orElseThrow(() -> new NotFoundProjectException(String.format(NOT_FOUND_PROJECT_MESSAGE, projectId)));

        List<Long> childIdList = new LinkedList<>();
        childIdList.add(projectId);
        getChildIdList(projectId, childIdList);

        return childIdList.stream()
                .map(id -> {
                    taskService.deleteTasksByProjectID(id);
                    return projectRepository.deleteProject(id);
                })
                .reduce((ac, el) -> ac + el)
                .get();
    }

    private void getChildIdList(long parentId, List<Long> childIdList){

        List<Long> foundedList = projectRepository.findChildId(parentId);

        foundedList.forEach(id -> {
            childIdList.add(0, id);
            getChildIdList(id, childIdList);
        });
    }
}