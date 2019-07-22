package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI extends InventoryList {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase ";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_REPORT = "";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_REPORT };

	private static final String SUB_MENU_OPTION_FEED_MONEY = "Feed money";
	private static final String SUB_MENU_OPTION_PURCHASE = "Select Product";
	private static final String SUB_MENU_OPTION_END = "Finish Transaction";
	private static final String[] SUB_MENU_OPTIONS = { SUB_MENU_OPTION_FEED_MONEY, SUB_MENU_OPTION_PURCHASE,
			SUB_MENU_OPTION_END };

	Scanner inputScanner = new Scanner(System.in);
	boolean keepLooping = true;
	private Menu menu;
	private InventoryList theInventory = new InventoryList();
	private static DecimalFormat formatted = new DecimalFormat("#.##");


	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);

		cli.run();

	}

	public void run() throws FileNotFoundException {

		// create audit text file at startup
		File auditFile = new File("TestAudit.txt");
		Audit currentAudit = new Audit(auditFile);
		File destFile = new File("SalesReport.txt");
		InventoryList salesReport = new InventoryList();
		try {
			auditFile.createNewFile();
		} catch (IOException e) {
			System.out.println("Unable to create audit text file during vending machine start up.");
		}

		// theInventory.testPrint(); //test audit writer is working by printing test at
		// startup
		while (keepLooping == true) {
			theInventory.setBalanceToZero();
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {

				System.out.println("Here's what We've got!");
				System.out.println();
				System.out.println(theInventory.printInventory());
				System.out.println();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				boolean usingSubMenu = true;
				while (usingSubMenu == true) {

					System.out.println("Current Balance: " + theInventory.getCurrentBalance());

					String subMenuChoice = (String) menu.getChoiceFromOptions(SUB_MENU_OPTIONS);
					if (subMenuChoice.equals(SUB_MENU_OPTION_FEED_MONEY)) {
						try {
							System.out.println("Please enter money in a whole dollar amount");
							int depositAmount = Integer.parseInt(inputScanner.nextLine());
							theInventory.addMoney(depositAmount);
							theInventory.updateLogDeposit(depositAmount, theInventory.getCurrentBalance());
							System.out.println(formatted.format(theInventory.getCurrentBalance()));
						} catch (NumberFormatException e) {
							System.out.println("Coins are not accepted. Please insert a whole dollar amount.");
						}
					} else if (subMenuChoice.equals(SUB_MENU_OPTION_PURCHASE)) {
						System.out.println("Please make a selection");
						String selection = inputScanner.nextLine();
						while (!theInventory.isValidSelection(selection)) {
							System.out.println("Invalid Selection!  Please choose another");
							selection = inputScanner.nextLine();
						}
						if (theInventory.isValidSelection(selection)) {
							if (theInventory.getCurrentBalance() < theInventory.getPriceOfSelection(selection)) {
								System.out.println("Sorry, you need more funds for this transaction.");
							} else {
								if (theInventory.getAmountOfProduct(selection) <= 0) {
									System.out.println("SOLD OUT");
								} else {
									theInventory.removeStock(selection);
									theInventory.makePurchase(selection);
									theInventory.updateLogPurchase(theInventory.getProductName(selection), selection,
											(theInventory.getPriceOfSelection(selection)
													+ theInventory.getCurrentBalance()),
											theInventory.getCurrentBalance());
									System.out.println(formatted.format(theInventory.getCurrentBalance()));
									System.out.println(theInventory.getAmountOfProduct(selection));
									System.out.println(theInventory.getSound(selection));
								}

							}

						}
					}

					else if (subMenuChoice.equals(SUB_MENU_OPTION_END)) {
						theInventory.updateLogMakeChange(theInventory.getCurrentBalance());
						System.out.println(theInventory.giveChange(theInventory.getCurrentBalance()));
						System.out.println("Have a great day!");
						currentBalance = 0;
						usingSubMenu = false;
					}
				}

			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				System.out.println("Come back soon!");
				keepLooping = false;

			} 
			
			else if (choice.equals(MAIN_MENU_OPTION_REPORT)) {
				    theInventory.generateSales();
				    theInventory.getSalesReport();
				    
				   } 
				/*currentAudit.printAudit(); // print the audit file as a .txt file
			}*/

		}
	}

}
