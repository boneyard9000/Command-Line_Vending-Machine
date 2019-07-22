/*package com.techelevator;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class Customer {

	double currentBalance = 0;
	InventoryList customerInventory = new InventoryList();
	private  Map<String, Product> customerMap = new LinkedHashMap<String, Product>();
	
	
	public double getCurrentBalance() {
		return currentBalance;
	}
	
	public double addMoney(int depositAmount) {
		currentBalance += depositAmount;
		return currentBalance;
	}
	
	public Map <String, Product> getVendingItems() {
		 customerMap = customerInventory.getVendingItems();
		 return customerMap;
	}
	
	public void makePurchase(String selection) {
		currentBalance -= customerInventory.getPriceOfSelection(selection);
	}
	
	public void makeAudit() {
		
	}
	
}
*/