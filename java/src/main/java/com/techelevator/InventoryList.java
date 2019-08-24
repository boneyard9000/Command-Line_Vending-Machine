package com.techelevator;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class InventoryList {
	


	
	private String productName;
	private String changeLine;	
	private double balance1;
	private int quarters;
	private int dime;
	private int nickels;
	private double totalSales = 0;
	private  Map<String, Product> vendingItems = new LinkedHashMap<String, Product>();
	List<Product> productList = new ArrayList<>();
	public double currentBalance = 0;
	int currentAmount;
	//Audit newAudit = new Audit();
	PrintWriter auditWriter = null;
	LocalDate today = LocalDate.now();
	LocalTime now = LocalTime.now();
	String printToday = today.toString();
	String printTime = now.toString();
	
	private List<String> salesFile = new ArrayList<String>(); 
	
	File auditFile = new File("TestAudit.txt");  
	
	
	
	
	
    public InventoryList() {


		File productFile = new File("VendingMachine.txt");
		
    	

		try (Scanner productScanner  = new Scanner(productFile);) {
			
			while(productScanner.hasNextLine()) {
			String eachItem = productScanner.nextLine(); 
				
			String[] itemArray = eachItem.split("\\|");
			
			String slot = itemArray[0];
			String name = itemArray[1];
			String priceString = itemArray[2];
			double price = Double.parseDouble(priceString);
			String kind = itemArray[3];
			
			if(kind.equals("Chip")) {
				Chips chip = new Chips(name, price, slot);
				productList.add(chip);
				vendingItems.put(slot, chip);

			}
			else if (kind.equals("Gum")) {
				Gum gum = new Gum(name, price, slot);
				productList.add(gum);
				vendingItems.put(slot, gum);

			}
			else if (kind.equals("Drink")) {
				Drink drink = new Drink(name, price, slot);
				productList.add(drink);
				vendingItems.put(slot, drink);

			}
			else if (kind.equals("Candy")) {
				Candy candy = new Candy(name, price, slot);
				productList.add(candy);
				vendingItems.put(slot, candy);
			}
			}
		}
		
		catch (FileNotFoundException e) {
			System.out.println("File not Found!");
		}
		
	}
    
    
		
	
	private String printString = "";
	
	 
	public String printInventory() {
		
		for (String key : vendingItems.keySet()) {
			if (vendingItems.get(key).getCurrentAmount() == 0) {
				System.out.println("Sold out!");
			}
			else {
			
			printString = vendingItems.get(key).getSlot() + ":  " + vendingItems.get(key).getName() + "   " + 
						(vendingItems.get(key).getPrice());
			System.out.println(printString);
			}
		}
		return "";
	}
	
	public String getProductName(String selection) {
		productName = vendingItems.get(selection).getName();
		return productName;
	}
	
	

	public double addMoney(double depositAmount) {
		
		LocalDate.now();
		
		currentBalance += depositAmount;
		return currentBalance;
	}
	
	public boolean isValidSelection(String selection) {
		boolean isValid = false;
		if (vendingItems.containsKey(selection)) {
			isValid = true;
		}
		return isValid;
		
	}
	
	public Map getVendingItems() {
		return vendingItems;
	}
	
	public double getPriceOfSelection(String selection) {
		double priceOfSelection = vendingItems.get(selection).getPrice();
		return priceOfSelection;
	}
	
	public int getAmountOfProduct(String selection) {
		currentAmount = vendingItems.get(selection).getCurrentAmount();
		return currentAmount;
	}
	
	public void removeStock(String selection) {
		vendingItems.get(selection).removeProduct();
	}
	public String getSound(String selection) {
		return vendingItems.get(selection).getSound();
	}	
	
	
	public double getCurrentBalance() {
		return currentBalance;
	}
	
	public File getAuditFile() {
		return auditFile;
	}
	
	
	public double addMoney(int depositAmount) {
		
		currentBalance += depositAmount;
		
		return currentBalance;
	}
	
	public double setBalanceToZero() {
		currentBalance = 0;
		return currentBalance;
	}
	
	public void makePurchase(String selection) {
		currentBalance -= getPriceOfSelection(selection);
		totalSales += getPriceOfSelection(selection);
		vendingItems.get(selection).trackSales();
	}
	
	public String giveChange(double balance) {
	
		balance1 = (balance * 100);
		
		
		quarters =  (int) (balance1 / 25);
		balance1 = balance1 - (quarters * 25);
		dime = ((int) balance1 / 10);
		balance1 = balance1 - (dime * 10);
		nickels = ((int) balance1 / 5);
		
		
		
		changeLine	= "Your change is " + quarters + " quarter(s) and " + dime + " dime(s) and " + nickels + " nickel(s). ";
		
		return changeLine;
	}
	
	public void updateLogDeposit(double depositAmount, double currentBalance) throws FileNotFoundException {
		LocalDate today = LocalDate.now();
		LocalTime now = LocalTime.now();
		String printToday = today.toString();
		String printTime = now.toString();
		
		auditWriter = new PrintWriter(new FileOutputStream("TestAudit.txt", true));
		auditWriter.println(printTime + " " + printToday + " FEED AMOUNT: $" + depositAmount + " Ending Balance: $" + currentBalance);
		auditWriter.close();
	}
	
	public void updateLogPurchase(String productName, String key, double startingBalance, double currentBalance) throws FileNotFoundException {
		LocalDate today = LocalDate.now();
		LocalTime now = LocalTime.now();
		String printToday = today.toString();
		String printTime = now.toString();
		
		auditWriter = new PrintWriter(new FileOutputStream("TestAudit.txt", true));
		auditWriter.println(printTime + " " + printToday + " " + productName + " " + key + " Starting Balance: $" + startingBalance +
										" Ending Balance: $" + currentBalance);
		auditWriter.close();
	}
	
	public void updateLogMakeChange(double currentBalance) throws FileNotFoundException {
		LocalDate today = LocalDate.now();
		LocalTime now = LocalTime.now();
		String printToday = today.toString();
		String printTime = now.toString();
		
		auditWriter = new PrintWriter(new FileOutputStream("TestAudit.txt", true));
		auditWriter.println(printTime + " " + printToday + " MAKE CHANGE: $" + currentBalance + 
										" Ending Balance: $0.00");
		auditWriter.close();
	}
	
	public void getSalesReport() {
   	 String fileName = " SalesReport.txt";
   	 File destFile = new File(fileName);
   	 try (PrintWriter writer = new PrintWriter( new FileOutputStream(destFile, true))) {
   	  if (destFile.createNewFile() || destFile.exists()) {
   	 	 writer.println();
   	 	 writer.println();
   	   for (Product item : productList) {
   	    writer.println(item.getName() + " Amount Sold: " + item.getNumberSold());
   	   }
   	   writer.println();
   	   writer.print("***TOTAL SALES*** $");
   	   writer.format("%.2f", totalSales);
   	   writer.println();
   	   writer.close();

   	   
   	  }else {
   	   System.out.println("Sales file to print to not found!");
   	  }
   	 } catch (FileNotFoundException e) {
   	  System.out.println("File not found!");
   	 } catch (IOException e) {
   	  System.out.println("Cannot create new file.");
   	 }
   	}
   public void generateSales() {
   	 for (String key : vendingItems.keySet()) {
   	  Product currentProduct = vendingItems.get(key);
   	  String productName = currentProduct.getName();
   	  int inventorySold = currentProduct.getNumberSold();
   	  String salesReportLine = String.format("%-22s", productName) + String.format("|%-3s|", inventorySold);
   	  salesFile.add(salesReportLine);
   	 }
   	}
	
	 
	/* test if audit printing is working by printing test to new file TestAudit.txt at vending machine startup
	public void testPrint() throws FileNotFoundException {
		auditWriter = new PrintWriter(new FileOutputStream("TestAudit.txt", true));

		auditWriter.println("test");
		auditWriter.close();
	}
	*/
	
	
	
}

