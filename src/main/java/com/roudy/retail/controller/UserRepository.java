package com.roudy.retail.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.roudy.retail.model.User;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(User user) {
        return jdbcTemplate.update(
                "insert into user (name, type, created_date) values(?,?, to_date(?, 'ddmmyyyy'))",
                user.getName(), user.getType(), user.getCreatedDate());
    }
    
    public String date2string(Date date) {
        String pattern = "ddMMyyyy";
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    public User findByUserId(Long id) {

        String sql = "SELECT * FROM USER WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRowMapper());

    }
    
    public User findByUserName(String name) {

        String sql = "SELECT * FROM USER WHERE NAME = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new UserRowMapper());

    }

    public List<User> findAll() {

        String sql = "SELECT * FROM USER";

        List<User> users = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map<?, ?> row : rows) {
            User obj = new User();

            obj.setID(((Integer) row.get("ID")).longValue());
            obj.setName((String) row.get("NAME"));
            obj.setType((String) row.get("TYPE"));
            obj.setCreatedDate(date2string((Date) row.get("CREATED_DATE")));
            users.add(obj);
        }

        return users;
    }

    public String findUserNameById(Long id) {

        String sql = "SELECT NAME FROM USER WHERE ID = ?";

        return jdbcTemplate.queryForObject(
                sql, new Object[]{id}, String.class);

    }

    public int count() {

        String sql = "SELECT COUNT(*) FROM USER";

        return jdbcTemplate.queryForObject(sql, Integer.class);

    }

}
