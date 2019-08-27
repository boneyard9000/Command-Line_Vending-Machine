package com.techelevator;

import java.math.BigDecimal;

public class Chips extends Product{
	
	public Chips(String name, BigDecimal price, String slot) {
		super(name, price, slot);
	}
	
	@Override
	public String getSound() {
		
		return "Crunch Crunch, Yum!";
	}

}
