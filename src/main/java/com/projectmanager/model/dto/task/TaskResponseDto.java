package com.projectmanager.model.dto.task;

import com.projectmanager.model.TaskStatus;
import com.projectmanager.model.TaskType;
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
    private TaskStatus taskStatus;
    private TaskType taskType;
    private String description;
    private String branch;
    private String managerDocs;
    private OffsetDateTime creationDate;
    private OffsetDateTime updateDate;
}