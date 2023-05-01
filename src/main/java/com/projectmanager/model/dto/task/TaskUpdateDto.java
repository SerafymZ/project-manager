package com.projectmanager.model.dto.task;

import com.projectmanager.validation.IsAvailableType;
import com.projectmanager.validation.IsAvailableStatus;
import com.projectmanager.validation.IsProjectExist;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdateDto {

    private Long id;

    @NotNull
    @IsProjectExist
    private Long projectId;
    private Long userId;

    @IsAvailableStatus
    private Long taskStatusId;

    @IsAvailableType
    private Long taskTypeId;

    private String description;
    private String branch;
    private String managerDocs;
}