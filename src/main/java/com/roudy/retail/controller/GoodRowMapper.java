package com.roudy.retail.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.roudy.retail.model.Good;

public class GoodRowMapper implements RowMapper<Good> {

    @Override
    public Good mapRow(ResultSet rs, int rowNum) throws SQLException {

        Good good = new Good();
        good.setID(rs.getLong("ID"));
        good.setName(rs.getString("NAME"));
        good.setType(rs.getString("TYPE"));
        good.setPrice(rs.getDouble("PRICE"));

        return good;

    }
}
