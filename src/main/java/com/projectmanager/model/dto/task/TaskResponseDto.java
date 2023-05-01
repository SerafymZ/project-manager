package com.projectmanager.model.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {

    private Long id;
    private Long projectId;
    private Long userId;
    private Long taskTypeId;
    private Long taskStatusId;
    private String description;
    private String branch;
    private String managerDocs;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}