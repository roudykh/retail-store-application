package com.roudy.retail.model;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Purchase {

	private static final Logger log = LoggerFactory.getLogger(Purchase.class);

	private Long ID;
	private HashMap<Good, Integer> basket;
	private User user;
	private double price;

	public Purchase() {
	}

	public Purchase(HashMap<Good, Integer> basket, User user) {
		this.basket = basket;
		this.user = user;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public HashMap<Good, Integer> getBasket() {
		return basket;
	}

	public void setBasket(HashMap<Good, Integer> basket) {
		this.basket = basket;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void checkout() {

		Date cutoffDate = java.sql.Date.valueOf(LocalDate.now().minusDays(730));
		double sum = 0;

		switch (user.getType()) {
		case "Employee":
			for (HashMap.Entry<Good, Integer> entry : basket.entrySet()) {
				Good good = entry.getKey();
				Integer quantity = entry.getValue();
				sum += good.getPrice() * quantity;
			}
			sum *= 0.7;
			break;
		case "Affliate":
			for (HashMap.Entry<Good, Integer> entry : basket.entrySet()) {
				Good good = entry.getKey();
				Integer quantity = entry.getValue();
				sum += good.getPrice() * quantity;
			}
			sum *= 0.9;
			break;
		case "Customer":
			try {
				if (string2date(user.getCreatedDate()).after(cutoffDate)) {
					for (HashMap.Entry<Good, Integer> entry : basket.entrySet()) {
						Good good = entry.getKey();
						Integer quantity = entry.getValue();
						sum += good.getPrice() * quantity;
					}
					if (sum >= 100)
						sum *= 0.95;
				} else {
					for (HashMap.Entry<Good, Integer> entry : basket.entrySet()) {
						Good good = entry.getKey();
						Integer quantity = entry.getValue();
						sum += good.getPrice() * quantity;
					}
					sum *= 0.95;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		
		DecimalFormat df = new DecimalFormat("####0.00");
		this.setPrice(Double.valueOf(df.format(sum)));
		
		log.info("The price is " + this.getPrice() + " for " + this.getUser().getName());
	}

	Date string2date(String str) throws Exception {
		DateFormat format = new SimpleDateFormat("ddMMyyyy");
		Date date = format.parse(str);
		return date;
	}
}
