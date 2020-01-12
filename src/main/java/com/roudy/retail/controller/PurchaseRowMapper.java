package com.roudy.retail.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.roudy.retail.model.Purchase;

public class PurchaseRowMapper implements RowMapper<Purchase> {

    @Override
    public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {

    	Purchase purchase = new Purchase();
    	purchase.setID(rs.getLong("ID"));
    	purchase.setPrice(rs.getDouble("PRICE"));

        return purchase;

    }
}
