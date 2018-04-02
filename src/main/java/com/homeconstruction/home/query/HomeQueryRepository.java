package com.homeconstruction.home.query;

import com.homeconstruction.home.api.ProjectNumber;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HomeQueryRepository {

    private final static String SELECT_CLAUSE = "SELECT * FROM home";

    private final JdbcTemplate jdbcTemplate;

    HomeQueryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Optional<HomeProjection> findByHousenumber(ProjectNumber projectNumber) {
        return jdbcTemplate.query(SELECT_CLAUSE + " WHERE project_number = ?",
                new Object[]{projectNumber.getProjectNumber()}, new BeanPropertyRowMapper(HomeProjection.class)).stream().findFirst();
    }
}