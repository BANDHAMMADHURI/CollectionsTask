package com.full.shopping.view;

import java.util.Scanner;

public class Choices {
	Scanner scan=new Scanner(System.in);
	public void options() {
		System.out.println("\t\t 1.show Products \n\t\t 2.Customer Purchased List \n\t\t 3.exit ");
	}
	public int getChoice() {
		
		int choice = scan.nextInt();
		return choice;
	}
	
}
