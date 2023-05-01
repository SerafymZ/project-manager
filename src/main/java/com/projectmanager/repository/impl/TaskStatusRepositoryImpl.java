package com.projectmanager.repository.impl;

import com.projectmanager.model.entity.TaskStatusEntity;
import com.projectmanager.repository.TaskStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TaskStatusRepositoryImpl implements TaskStatusRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public Optional<TaskStatusEntity> findTaskStatusById(Long statusId) {

        var sql = "SELECT ID, "
                + "taskStatus FROM TaskStatus "
                + "WHERE ID = :statusId";

        var parameterSource = new MapSqlParameterSource("statusId", statusId);

        try {
            TaskStatusEntity result = jdbcTemplate.queryForObject(
                    sql,
                    parameterSource,
                    new BeanPropertyRowMapper<>(TaskStatusEntity.class)
            );

            return Optional.of(result);
        } catch(EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }
}