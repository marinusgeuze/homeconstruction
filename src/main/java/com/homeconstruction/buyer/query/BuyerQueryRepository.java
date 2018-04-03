package com.homeconstruction.buyer.query;

import com.homeconstruction.buyer.api.BuyerId;
import com.homeconstruction.buyer.api.Surname;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BuyerQueryRepository {

    private final static String SELECT_CLAUSE = "SELECT * FROM buyer";

    private final JdbcTemplate jdbcTemplate;

    BuyerQueryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    Optional<BuyerProjection> findById(BuyerId buyerId) {

        return jdbcTemplate.query(SELECT_CLAUSE + " WHERE id = ?",
                new Object[]{buyerId.getId()}, new BeanPropertyRowMapper(BuyerProjection.class)).stream().findFirst();
    }

    public Optional<BuyerProjection> findBySurname(Surname surname) {

        return jdbcTemplate.query(SELECT_CLAUSE + " WHERE surname = ?",
                new Object[]{surname.getSurname()}, new BeanPropertyRowMapper(BuyerProjection.class)).stream().findFirst();
    }
}