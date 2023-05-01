package com.projectmanager.model.dto.project;

import com.projectmanager.validation.IsProjectExist;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDto {

    @IsProjectExist
    private Long parentId;

    @NotBlank
    private String name;

    private String description;
}