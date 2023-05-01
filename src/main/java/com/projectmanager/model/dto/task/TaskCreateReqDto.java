package com.projectmanager.model.dto.task;

import com.projectmanager.validation.IsAvailableType;
import com.projectmanager.validation.IsProjectExist;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateReqDto {

    @NotNull
    @IsProjectExist
    private Long projectId;

    @IsAvailableType
    private Long taskTypeId;

    private Long userId;

    private String description;
    private String branch;
    private String managerDocs;
}