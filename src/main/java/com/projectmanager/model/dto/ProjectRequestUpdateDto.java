package com.projectmanager.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestUpdateDto {

    private Long id;

    private Long parentId;

    @NotBlank
    private String name;

    private String description;
}