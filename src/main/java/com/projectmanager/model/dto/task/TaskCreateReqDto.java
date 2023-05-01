package com.projectmanager.model.dto.task;

import com.projectmanager.model.TaskType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreateReqDto {

    @NotNull
    private Long projectId;

    @NotNull
    @Min(1)
    @Max(2)
    TaskType taskTypeId;

    private String description;
    private String branch;
    private String managerDocs;
}