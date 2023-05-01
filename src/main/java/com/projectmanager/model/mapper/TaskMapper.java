package com.projectmanager.model.mapper;

import com.projectmanager.model.dto.task.TaskCreateReqDto;
import com.projectmanager.model.dto.task.TaskResponseDto;
import com.projectmanager.model.dto.task.TaskUpdateDto;
import com.projectmanager.model.entity.TaskCreateEntity;
import com.projectmanager.model.entity.TaskEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TaskMapper {

    TaskResponseDto toResponseDto(TaskEntity taskEntity);

    TaskCreateEntity toCreateEntity(TaskCreateReqDto reqDto);

    TaskEntity toEntity(TaskUpdateDto updateDto);
}
