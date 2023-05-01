package com.projectmanager.repository.impl;

import com.projectmanager.exception.SqlException;
import com.projectmanager.model.entity.TaskEntity;
import com.projectmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<TaskEntity> findAllTasks() {

        var sql = "SELECT ID, "
                + "projectID, "
                + "userID, "
                + "status, "
                + "description, "
                + "branch, "
                + "managerDocs, "
                + "creationDate, "
                + "updateDate FROM Task";

        try {
            return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TaskEntity.class));
        } catch (DataAccessException exception) {
            throw new SqlException("Error during find all tasks")    ;
        }
    }
}
