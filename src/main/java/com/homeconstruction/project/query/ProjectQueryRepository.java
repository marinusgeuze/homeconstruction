package com.homeconstruction.project.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class ProjectQueryRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    String SELECT_CLAUSE = "SELECT * FROM project";

    Collection<ProjectProjection> findAll() {

        return jdbcTemplate.query(SELECT_CLAUSE,
                new BeanPropertyRowMapper(ProjectProjection.class));
    }

    Optional<ProjectProjection> findById(String id) {

        return jdbcTemplate.query(SELECT_CLAUSE + " WHERE id = ?",
                new Object[]{id}, new BeanPropertyRowMapper(ProjectProjection.class)).stream().findFirst();
    }

    Optional findByName(String name) {
        return jdbcTemplate.query(SELECT_CLAUSE + " WHERE name = ?",
                new Object[]{name}, new BeanPropertyRowMapper(ProjectProjection.class)).stream().findFirst();
    }
}
