package com.roudy.retail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.roudy.retail.model.Good;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class GoodRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Good good) {
        return jdbcTemplate.update(
                "insert into good (name, type, price) values (?,?,?)",
                good.getName(), good.getType(), good.getPrice());
    }

    public Good findByGoodId(Long id) {

        String sql = "SELECT * FROM GOOD WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new GoodRowMapper());

    }

    public Good findByGoodId2(Long id) {

        String sql = "SELECT * FROM GOOD WHERE ID = ?";

        return (Good) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<Good>(Good.class));

    }

    public Good findByGoodId3(Long id) {

        String sql = "SELECT * FROM GOOD WHERE ID = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
                new Good(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getDouble("price")
                ));

    }

    public List<Good> findAll() {

        String sql = "SELECT * FROM GOOD";

        List<Good> goods = new ArrayList<>();

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        for (Map<?, ?> row : rows) {
            Good obj = new Good();

            obj.setID(((Integer) row.get("ID")).longValue());
            obj.setName((String) row.get("NAME"));
            obj.setType((String) row.get("TYPE"));
            obj.setPrice(Double.parseDouble(row.get("PRICE")+""));
            goods.add(obj);
        }

        return goods;
    }

    public List<Good> findAll2() {

        String sql = "SELECT * FROM GOOD";

        List<Good> goods = jdbcTemplate.query(
                sql,
                new GoodRowMapper());

        return goods;
    }

    public List<Good> findAll3() {

        String sql = "SELECT * FROM GOOD";

        List<Good> goods = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<Good>(Good.class));

        return goods;
    }

    public List<Good> findAll4() {

        String sql = "SELECT * FROM GOOD";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) ->
                        new Good(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("type"),
                                rs.getDouble("price")
                        )
        );
    }

    public String findUserNameById(Long id) {

        String sql = "SELECT NAME FROM GOOD WHERE ID = ?";

        return jdbcTemplate.queryForObject(
                sql, new Object[]{id}, String.class);

    }

    public int count() {

        String sql = "SELECT COUNT(*) FROM GOOD";

        return jdbcTemplate.queryForObject(sql, Integer.class);

    }

}
