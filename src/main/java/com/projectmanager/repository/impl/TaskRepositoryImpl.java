package com.projectmanager.repository.impl;

import com.projectmanager.exception.SqlException;
import com.projectmanager.model.entity.TaskEntity;
import com.projectmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private BeanPropertyRowMapper<TaskEntity> rowMapper = new BeanPropertyRowMapper<>(TaskEntity.class);

    @Override
    public List<TaskEntity> findAllTasks() {

        var sql = "SELECT t.ID, "
                + "t.projectID, "
                + "t.userID, "
                + "tt.taskType, "
                + "ts.taskStatus, "
                + "t.description, "
                + "t.branch, "
                + "t.managerDocs, "
                + "t.creationDate, "
                + "t.updateDate FROM Task AS t "
                + "JOIN TaskType AS tt ON t.taskTypeId = tt.ID "
                + "JOIN TaskStatus AS ts ON t.statusId = ts.ID";

        try {
            return namedParameterJdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TaskEntity.class));
        } catch (DataAccessException exception) {
            throw new SqlException("Error during find all tasks")    ;
        }
    }

    @Override
    public Optional<TaskEntity> findTaskById(long id) {

        var sql = "SELECT t.ID, "
                + "t.projectID, "
                + "t.userID, "
                + "tt.taskType, "
                + "ts.taskStatus, "
                + "t.description, "
                + "t.branch, "
                + "t.managerDocs, "
                + "t.creationDate, "
                + "t.updateDate FROM Task AS t "
                + "JOIN TaskType AS tt ON t.taskTypeId = tt.ID AND ID = :taskId "
                + "JOIN TaskStatus AS ts ON t.statusId = ts.ID";

        var parameterSource = new MapSqlParameterSource("taskId", id);

        try {
            TaskEntity result = namedParameterJdbcTemplate.queryForObject(sql, parameterSource, rowMapper);
            return Optional.of(result);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public int deleteTasksByProjectId(long projectId) {

        var sql = "DELETE FROM Task WHERE projectID = :projectId";

        var parameterSource = new MapSqlParameterSource("projectId", projectId);

        try {
            return namedParameterJdbcTemplate.update(sql, parameterSource);
        } catch (DataAccessException exception) {
            throw new SqlException("Error during delete tasks by project id.")    ;
        }
    }

    @Override
    public int deleteTaskById(Long id) {

        var sql = "DELETE FROM Task WHERE ID = :taskId";

        var parameterSource = new MapSqlParameterSource("taskId", id);

        try {
            return namedParameterJdbcTemplate.update(sql, parameterSource);
        } catch (DataAccessException exception) {
            throw new SqlException("Error during delete task by id.")    ;
        }
    }
}