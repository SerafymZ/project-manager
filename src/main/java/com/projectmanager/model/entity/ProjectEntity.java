package com.projectmanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectEntity {
    Long id;
    Long parentId;
    String name;
    String description;
}
