package com.homeconstruction.home.query;

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

    Optional<HomeProjection> findByHousenumber(Integer projectNumber) {
        return jdbcTemplate.query(SELECT_CLAUSE + " WHERE project_number = ?",
                new Object[]{projectNumber}, new BeanPropertyRowMapper(HomeProjection.class)).stream().findFirst();
    }
}