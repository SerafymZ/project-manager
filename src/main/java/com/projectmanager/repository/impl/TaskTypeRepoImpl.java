package com.projectmanager.repository.impl;

import com.projectmanager.model.entity.TaskTypeEntity;
import com.projectmanager.repository.TaskTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TaskTypeRepoImpl implements TaskTypeRepo {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<TaskTypeEntity> findTaskTypeById(Long typeId) {

        var sql = "SELECT ID, "
                + "taskType FROM TaskType "
                + "WHERE ID = :typeId";

        var parameterSource = new MapSqlParameterSource("typeId", typeId);

        try {
            TaskTypeEntity result =  jdbcTemplate.queryForObject(
                    sql,
                    parameterSource,
                    new BeanPropertyRowMapper<>(TaskTypeEntity.class)
            );

            return Optional.of(result);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }
}