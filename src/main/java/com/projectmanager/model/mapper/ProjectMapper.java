package com.projectmanager.model.mapper;

import com.projectmanager.model.dto.ProjectResponseDto;
import com.projectmanager.model.entity.ProjectEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProjectMapper {
    ProjectResponseDto toResponseDto(ProjectEntity entity);
}
