package com.homeconstruction.home.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HomeQueryRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private String SELECT_CLAUSE = "SELECT * FROM home";

    Optional<HomeProjection> findByHousenumber(Integer projectNumber) {
        return jdbcTemplate.query(SELECT_CLAUSE + " WHERE project_number = ?",
                new Object[]{projectNumber}, new BeanPropertyRowMapper(HomeProjection.class)).stream().findFirst();
    }
}