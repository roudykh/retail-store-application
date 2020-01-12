package com.roudy.retail.model;

import java.util.HashMap;

public class Basket {

	private Long ID;
	private HashMap<Good, Integer> goodsQtity;

	public Basket() {
	}

	public Basket(HashMap<Good, Integer> basket) {
		setGoodsQtity(basket);
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public HashMap<Good, Integer> getGoodsQtity() {
		return goodsQtity;
	}

	public void setGoodsQtity(HashMap<Good, Integer> goodsQtity) {
		this.goodsQtity = goodsQtity;
	}

}
