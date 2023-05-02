package com.projectmanager.model.dto.task;

import com.projectmanager.validation.IsAvailableType;
import com.projectmanager.validation.IsProjectExist;
import com.projectmanager.validation.IsUserExist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @IsUserExist
    private Long userId;

    @Size(max = 255)
    private String description;

    @Size(max = 100)
    private String branch;

    @Size(max = 255)
    private String managerDocs;
}