package com.roudy.retail.model;

import java.util.HashMap;

public class Basket {

	private Long id;
	private HashMap<Good, Integer> goodsQtity;

	public Basket() {
	}

	public Basket(HashMap<Good, Integer> basket) {
		setGoodsQtity(basket);
	}

	public Long getID() {
		return id;
	}

	public void setID(Long id) {
		this.id = id;
	}

	public HashMap<Good, Integer> getGoodsQtity() {
		return goodsQtity;
	}

	public void setGoodsQtity(HashMap<Good, Integer> goodsQtity) {
		this.goodsQtity = goodsQtity;
	}

}
