package com.projectmanager.repository.impl;

import com.projectmanager.model.entity.UserEntity;
import com.projectmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<UserEntity> findByName(String username) {

        var sql = "SELECT ID, "
                + "username, "
                + "password, "
                + "authority FROM UserDetail "
                + "WHERE username = :username";

        var parameterSource = new MapSqlParameterSource("username", username);

        try {
            UserEntity foundedEntity = jdbcTemplate.queryForObject(sql, parameterSource, new BeanPropertyRowMapper<>(UserEntity.class));
            return Optional.of(foundedEntity);
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }

    }
}