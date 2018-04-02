package com.homeconstruction.project.query;

import com.homeconstruction.project.api.ProjectId;
import com.homeconstruction.project.api.ProjectName;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class ProjectQueryRepository {

    private final static String SELECT_CLAUSE = "SELECT * FROM project";

    private final JdbcTemplate jdbcTemplate;

    ProjectQueryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Collection<ProjectProjection> findAll() {

        return jdbcTemplate.query(SELECT_CLAUSE,
                new BeanPropertyRowMapper(ProjectProjection.class));
    }

    Optional<ProjectProjection> findById(ProjectId id) {

        return jdbcTemplate.query(SELECT_CLAUSE + " WHERE id = ?",
                new Object[]{id.getId()}, new BeanPropertyRowMapper(ProjectProjection.class)).stream().findFirst();
    }

    Optional<ProjectProjection> findByName(ProjectName name) {
        return jdbcTemplate.query(SELECT_CLAUSE + " WHERE name = ?",
                new Object[]{name.getName()}, new BeanPropertyRowMapper(ProjectProjection.class)).stream().findFirst();
    }
}
