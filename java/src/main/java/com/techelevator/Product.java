package com.techelevator;

import java.math.BigDecimal;

public abstract class Product{
	
	private String slot = "";
	private double price = 0;
	private String name = "";
	private String kind = "";
	private String sound;
	private int amountOfProduct = 5;
	private int numberSold = 0;
	
	
	
	
	public Product (String name, double price, String slot) {
		this.name = name;
		this.price = price;
		this.slot = slot;
		amountOfProduct = 5;

	}
	
	public void removeProduct() {
	amountOfProduct --;
	}
	
	public void setAmountOfProduct(int amountOfProduct) {
		this.amountOfProduct = amountOfProduct;
	}
	
	public int getCurrentAmount() {
		return amountOfProduct;
	}

	public String getSlot() {
		return slot;
	}
	public abstract String getSound();

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public String getKind() {
		return kind;
	}

	public void setSlot(String slot) {
		this.slot = slot;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
	public void trackSales() {
		numberSold ++;
		
	}
	public int getNumberSold() {
		return numberSold;
	}
	

}
