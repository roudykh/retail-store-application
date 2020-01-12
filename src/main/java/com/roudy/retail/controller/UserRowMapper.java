package com.roudy.retail.controller;

import org.springframework.jdbc.core.RowMapper;

import com.roudy.retail.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {

        User user = new User();
        user.setID(rs.getLong("ID"));
        user.setName(rs.getString("NAME"));
        user.setType(rs.getString("TYPE"));
        user.setCreatedDate(date2string(rs.getDate("created_date")));

        return user;

    }
    
    public String date2string(Date date) {
        String pattern = "ddMMyyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        String dateAsString = df.format(date);
        return dateAsString;
    }
}
