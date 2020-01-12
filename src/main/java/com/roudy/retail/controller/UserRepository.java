package com.roudy.retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.roudy.retail.model.User;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        String dateAsString = df.format(date);
        return dateAsString;
    }

    public User findByUserId(Long id) {

        String sql = "SELECT * FROM USER WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRowMapper());

    }

    public User findByUserId2(Long id) {

        String sql = "SELECT * FROM USER WHERE ID = ?";

        return (User) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<User>(User.class));

    }

    public User findByUserId3(Long id) {

        String sql = "SELECT * FROM USER WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        date2string(rs.getDate("created_date"))
                ));

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

    public List<User> findAll2() {

        String sql = "SELECT * FROM USER";

        List<User> users = jdbcTemplate.query(
                sql,
                new UserRowMapper());

        return users;
    }

    public List<User> findAll3() {

        String sql = "SELECT * FROM USER";

        List<User> users = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<User>(User.class));

        return users;
    }

    public List<User> findAll4() {

        String sql = "SELECT * FROM USER";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new User(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("type"),
                                date2string(rs.getDate("created_date"))
                        )
        );
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
