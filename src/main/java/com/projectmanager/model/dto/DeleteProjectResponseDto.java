package com.projectmanager.model.dto;

import lombok.Data;

@Data
public class DeleteProjectResponseDto {
    private Status status;
    private String message;
}