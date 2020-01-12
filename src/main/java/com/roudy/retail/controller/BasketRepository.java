package com.roudy.retail.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.roudy.retail.model.Basket;
import com.roudy.retail.model.Good;

@Repository
public class BasketRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	GoodRepository goodRepository;

	public int save(Basket basket, long id) {
		int nbrOfRowsAffected = 0;
		basket.getGoodsQtity().forEach((k, v) -> {
			jdbcTemplate.update("insert into basket (id, goodid, quantity) values(?,?,?)", id, k.getID(), v);
		});
		return nbrOfRowsAffected;
	}

	public HashMap<Good, Integer> findBasketById(Long id) {

		String sql = "SELECT * FROM BASKET WHERE ID = ?";

		HashMap<Good, Integer> basket = new HashMap<>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id);

		for (Map<?, ?> row : rows) {
			Long goodid = (Long) row.get("GOODID");
			Good good = goodRepository.findByGoodId(goodid);
			int quantity = (int) row.get("QUANTITY");
			basket.put(good, quantity);
		}

		return basket;
	}

}
