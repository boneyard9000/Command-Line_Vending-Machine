package com.techelevator;

import java.math.BigDecimal;

public class Drink extends Product {

	public Drink(String name, BigDecimal price, String slot) {
		super(name, price, slot);
		}
	
	@Override
	public String getSound() {
		return "Glug Glug, Yum!";
	}
}
