package com.techelevator;

import org.junit.Test;

import org.junit.Assert;

public class InventoryListTest {

	
	private InventoryList inventory = new InventoryList();
	private boolean isTrue;
	
	
	@Test
	public void get_Product_ByName_Returns_Correct_ProductName() {
		String product1 = inventory.getProductName("A1");
		String product2 = inventory.getProductName("C4");
		String product3 = inventory.getProductName("D2");
		Assert.assertEquals("Potato Crisps", product1);
		Assert.assertEquals("Heavy", product2);
		Assert.assertEquals("Little League Chew", product3);
	}
	
	@Test
	public void is_Valid_Selection_Returns_Correct_Boolean() {
		boolean selection1 = inventory.isValidSelection("C3");
		boolean selection2 = inventory.isValidSelection("3C");
		boolean selection3 = inventory.isValidSelection("B1");
		boolean selection4 = inventory.isValidSelection("A5");
		Assert.assertEquals(true, selection1);
		Assert.assertEquals(false, selection2);
		Assert.assertEquals(true, selection3);
		Assert.assertEquals(false, selection4);
	}
	

	
}
