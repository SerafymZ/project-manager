package com.projectmanager.model.mapper;

import com.projectmanager.model.dto.task.TaskCreateReqDto;
import com.projectmanager.model.dto.task.TaskResponseDto;
import com.projectmanager.model.dto.task.TaskUpdateDto;
import com.projectmanager.model.entity.TaskCreateEntity;
import com.projectmanager.model.entity.TaskEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TaskMapper {

    @Mapping(source = "taskEntity.taskTypeId", target = "taskType.id")
    @Mapping(source = "taskEntity.taskType", target = "taskType.type")
    @Mapping(source = "taskEntity.taskStatusId", target = "taskStatus.id")
    @Mapping(source = "taskEntity.taskStatus", target = "taskStatus.status")
    TaskResponseDto toResponseDto(TaskEntity taskEntity);

    TaskCreateEntity toCreateEntity(TaskCreateReqDto reqDto);

    TaskEntity toEntity(TaskUpdateDto updateDto);
}
