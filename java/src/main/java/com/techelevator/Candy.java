package com.techelevator;

import java.math.BigDecimal;

public class Candy extends Product{

	public Candy(String name, double price, String slot) {
		super(name, price, slot);
	}
	
	@Override
	public String getSound(){
		
		return "Crunch Crunch, Yum!";		
	}

}
