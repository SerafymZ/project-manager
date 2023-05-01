package com.projectmanager.model.dto.task;

import com.projectmanager.validation.AvailableStatusValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusUpdateDto {

    private Long taskId;

    @AvailableStatusValue
    String taskStatus;
}
