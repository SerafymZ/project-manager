package com.projectmanager.repository;

import com.projectmanager.model.entity.ProjectEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    List<ProjectEntity> findAllProjects();
    Optional<ProjectEntity> findProjectById(long id);
    ProjectEntity saveProject(ProjectEntity sourceEntity);
    ProjectEntity updateProject(ProjectEntity sourceEntity);
    int deleteProject(long id);
}