package com.projectmanager.repository.impl;

import com.projectmanager.model.entity.ProjectEntity;
import com.projectmanager.model.exception.SqlException;
import com.projectmanager.repository.ProjectRepository;
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
public class ProjectRepositoryImpl implements ProjectRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final BeanPropertyRowMapper<ProjectEntity> rowMapper = new BeanPropertyRowMapper<>(ProjectEntity.class);

    @Override
    public List<ProjectEntity> findAllProjects() {

        var sql = "SELECT id, "
                + "parentID, "
                + "name, "
                + "description FROM Project";

        try {
            return namedParameterJdbcTemplate.query(sql, rowMapper);
        } catch (DataAccessException dae) {
            throw new SqlException("Error during find all projects");
        }
    }

    @Override
    public Optional<ProjectEntity> findProjectById(long id) {

        var sql = "SELECT id, "
                + "parentID, "
                + "name, "
                + "description FROM Project "
                + "WHERE id = :projectId";

        var parameterSource = new MapSqlParameterSource()
                .addValue("projectId", id);

        try {
            ProjectEntity projectEntity = namedParameterJdbcTemplate.queryForObject(sql, parameterSource, rowMapper);
            return Optional.of(projectEntity);
        }catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }
}
