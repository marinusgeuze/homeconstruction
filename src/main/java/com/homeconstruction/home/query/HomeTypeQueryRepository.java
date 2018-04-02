package com.homeconstruction.home.query;

import com.homeconstruction.home.api.HomeTypeId;
import com.homeconstruction.home.api.HomeTypeKey;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HomeTypeQueryRepository {

    private final static String SELECT_CLAUSE = "SELECT * FROM home_type";

    private final JdbcTemplate jdbcTemplate;

    HomeTypeQueryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<HomeTypeProjection> findById(HomeTypeId id) {

        return jdbcTemplate.query(SELECT_CLAUSE + " WHERE id = ?",
                new Object[]{id.getId()}, new BeanPropertyRowMapper(HomeTypeProjection.class)).stream().findFirst();
    }

    Optional<HomeTypeProjection> findByHomeType(HomeTypeKey homeTypeKey) {
        return jdbcTemplate.query(SELECT_CLAUSE + " WHERE type_key = ?",
                new Object[]{homeTypeKey.getTypeKey()}, new BeanPropertyRowMapper(HomeTypeProjection.class)).stream().findFirst();
    }
}