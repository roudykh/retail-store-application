package com.roudy.retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.roudy.retail.model.Purchase;

@Repository
public class PurchaseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Purchase purchase, Long basketId) {
        return jdbcTemplate.update(
                "insert into purchase (userid, basketid, price) values(?,?,?)",
                purchase.getUser().getID(), basketId, purchase.getPrice());
    }

    public Purchase findByPurchaseId(Long id) {

        String sql = "SELECT * FROM PURCHASE WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new PurchaseRowMapper());

    }

    public int count() {

        String sql = "SELECT COUNT(*) FROM PURCHASE";

        return jdbcTemplate.queryForObject(sql, Integer.class);

    }

}
