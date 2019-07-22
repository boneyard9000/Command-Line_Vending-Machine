package com.techelevator;

import java.math.BigDecimal;

public class Gum extends Product{

	public Gum(String name, double price, String slot) {
		super(name, price, slot);
	}
	
	@Override
	public String getSound() {
		return "Chew Chew, Yum!";
	}
	

}
