package com.projectmanager.model.mapper;

import com.projectmanager.model.dto.project.ProjectRequestDto;
import com.projectmanager.model.dto.project.ProjectRequestUpdateDto;
import com.projectmanager.model.dto.project.ProjectResponseDto;
import com.projectmanager.model.entity.ProjectEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProjectMapper {

    ProjectResponseDto toResponseDto(ProjectEntity entity);

    ProjectEntity toEntity(ProjectRequestDto requestDto);

    ProjectEntity toEntity(ProjectRequestUpdateDto requestDto);
}