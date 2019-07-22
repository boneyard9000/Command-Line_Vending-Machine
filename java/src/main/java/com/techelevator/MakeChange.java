package com.techelevator;

public class MakeChange {
	
	private double balance1;
	private int quarters;
	private int dime;
	private int nickels;

	public void giveChange(double balance) {

		balance1 = (balance * 100);
		quarters =  (int) (balance1 / 25);
		balance1 = balance1 - (quarters * 25);
		dime = ((int) balance1 / 10);
		balance1 = balance1 - (dime * 10);
		nickels = ((int) balance1 / 5);

		balance = 0;
		System.out.println(
				"Your change is " + quarters + " quarters and " + dime + " dimes and " + nickels + " nickels. ");
	}

}
