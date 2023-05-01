package com.projectmanager.model.dto.task;

import com.projectmanager.validation.AvailableTypeValue;
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

    @NotNull
    @AvailableTypeValue
    private String taskType;

    private String description;
    private String branch;
    private String managerDocs;
}