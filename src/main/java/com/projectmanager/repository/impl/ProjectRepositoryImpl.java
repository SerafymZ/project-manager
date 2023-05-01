package com.projectmanager.repository.impl;

import com.projectmanager.model.entity.ProjectEntity;
import com.projectmanager.exception.SqlException;
import com.projectmanager.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
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

        var sql = "SELECT ID, "
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

        var sql = "SELECT ID, "
                + "parentID, "
                + "name, "
                + "description FROM Project "
                + "WHERE ID = :projectId";

        var parameterSource = new MapSqlParameterSource("projectId", id);

        try {
            ProjectEntity projectEntity = namedParameterJdbcTemplate.queryForObject(sql, parameterSource, rowMapper);
            return Optional.of(projectEntity);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public int saveProject(ProjectEntity sourceEntity) {

        var sql = "INSERT INTO Project(parentid, name, description) VALUES (:parentId, :name, :description)";

        var parameterSource = new MapSqlParameterSource()
                .addValue("parentId", sourceEntity.getParentId())
                .addValue("name", sourceEntity.getName())
                .addValue("description", sourceEntity.getDescription());

        try {
            return namedParameterJdbcTemplate.update(sql, parameterSource);
        } catch (DataAccessException dae) {
            throw new SqlException("Error during save project.");
        }
    }

    @Override
    public int updateProject(ProjectEntity sourceEntity) {

        var sql = "UPDATE Project SET parentID = :parentId, name = :name, description = :description "
                + "WHERE ID = :id";

        var parameterSource = new MapSqlParameterSource()
                .addValue("id", sourceEntity.getId())
                .addValue("parentId", sourceEntity.getParentId())
                .addValue("name", sourceEntity.getName())
                .addValue("description", sourceEntity.getDescription());

        try {
            return namedParameterJdbcTemplate.update(sql, parameterSource);
        } catch (DataAccessException dae) {
            throw new SqlException("Error during update project by id");
        }
    }

    @Override
    public int deleteProject(long id) {

        var sql = "DELETE FROM Project WHERE ID = :id";

        var parameterSource = new MapSqlParameterSource("id", id);
        try {
            return namedParameterJdbcTemplate.update(sql, parameterSource);
        } catch (DataAccessException dae) {
            throw new SqlException("Error during delete project by id");
        }
    }

    @Override
    public List<Long> findChildId(long parentId) {

        var sql = "SELECT ID FROM Project WHERE parentID = :parentId";

        var parameterSource = new MapSqlParameterSource("parentId", parentId);

        try {
            return namedParameterJdbcTemplate.query(sql, parameterSource, new SingleColumnRowMapper<>(Long.class));
        } catch (DataAccessException dae) {
            throw new SqlException("Error during find child project ids");
        }
    }
}