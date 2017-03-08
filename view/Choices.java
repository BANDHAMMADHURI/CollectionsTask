package com.full.shopping;

import java.util.Scanner;

public class Choices {
	String userInput, choice;
	Scanner scan = new Scanner(System.in);

	public void options() {
		System.out.println("\t\t 1.store Products \n\t\t 2. Add Product\n\t    3.Customer Purchased List \n\t\t    4.exit ");
		System.out.println("\n\t\tChoose your option\n");
	}

	public String getChoice() {

		choice = scan.next();
		return choice;

	}

	public String inputCheck() {
		
			String choice1 = scan.next();
do{
			switch (choice1.toLowerCase()) {
			case "yes":
				choice = choice1;
				break;
			case "no":choice=choice1;
				break;
			default:
				System.out.println("enter valid input(yes/no)");
				choice = scan.next();
				break;
			}}while(!(choice.equalsIgnoreCase("yes"))&&!(choice.equalsIgnoreCase("no")));
		
		return choice;
	}

	/*
	 * public String inputCheck() throws Exception { userInput=scan.next(); try
	 * { if
	 * ((userInput.equalsIgnoreCase("yes"))||(scan.next().equalsIgnoreCase("no")
	 * )) { return userInput;
	 * 
	 * } else throw new Exception(); } catch (Exception e) {
	 * System.out.println("Enter Valid Input"); }
	 * 
	 * return userInput; }
	 */
}
