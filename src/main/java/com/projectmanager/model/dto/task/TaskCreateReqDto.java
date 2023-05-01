package com.projectmanager.model.dto.task;

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
    private String description;
    private String branch;
    private String managerDocs;
}