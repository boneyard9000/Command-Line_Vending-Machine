package com.techelevator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Audit {
	
	InventoryList auditInventory = new InventoryList();
	Scanner auditScanner;
	public Audit(File auditFile){
	}
		
	public void printAudit() {
	//go through audit file and print each line to console
	try {
		auditScanner = new Scanner(auditInventory.getAuditFile());
	
		
		while (auditScanner.hasNextLine()) {
			String line = auditScanner.nextLine();
			System.out.println(line);
			
		}
	}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
	
	
	
	/*InventoryList auditInventory = new InventoryList();
	
	public static void feedMoney(double depositAmount, double currentBalance) throws FileNotFoundException {
		writeToLogFile("FEED MONEY", depositAmount, currentBalance);
		
	}
	public static void giveChange(double currentBalance, double balance) throws FileNotFoundException {
		writeToLogFile ("GIVE CHANGE", currentBalance, balance);
	}
	public static void itemSold(String name,String key) throws FileNotFoundException {
		writeToLogFile ("tester", firstNum, secondNum);
	}
	
	
	private static void writeToLogFile(String message, double firstNum, double secondNum) throws FileNotFoundException {

		
		LocalDate today = LocalDate.now();
		LocalTime now = LocalTime.now();
		// write message
		// Don't forget your padding
		File auditFile = new File("TestAudit.txt");

		try(PrintWriter auditWriter = new PrintWriter(auditFile);){
			
			String printToday = today.toString();
			String printTime = now.toString();
			
			
			
			
		
		}
		
		
		
		
		catch(FileNotFoundException e) {
			
		}
		
	}
	
		*/
	



