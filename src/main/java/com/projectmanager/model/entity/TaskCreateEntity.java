package com.projectmanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateEntity {

    private Long projectId;
    private Long taskTypeId;
    private String description;
    private String branch;
    private String managerDocs;
}