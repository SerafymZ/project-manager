package com.projectmanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskEntity {
    private Long id;
    private Long projectId;
    private Long userId;
    private Long taskTypeId;
    private String taskType;
    private Long taskStatusId;
    private String taskStatus;
    private String description;
    private String branch;
    private String managerDocs;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}