package com.projectmanager.model.dto.task;

import com.projectmanager.validation.IsAvailableType;
import com.projectmanager.validation.IsAvailableStatus;
import com.projectmanager.validation.IsProjectExist;
import com.projectmanager.validation.IsUserExist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @IsUserExist
    private Long userId;

    @IsAvailableStatus
    private Long taskStatusId;

    @IsAvailableType
    private Long taskTypeId;

    @Size(max = 255)
    private String description;

    @Size(max = 100)
    private String branch;

    @Size(max = 100)
    private String managerDocs;
}