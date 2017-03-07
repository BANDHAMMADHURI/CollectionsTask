package com.full.shopping;

import static java.lang.System.out;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ShoppingCart {
	static int remain;
	static String customerName, item;
	static int availability;
	static String customerChoice,userItems;
	static Integer itemQuantity;
	static Map<String, Integer> customerItems;
	static List<String> customersList=new LinkedList<String>();
 	
	static Map<String, Integer> storeProducts = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
	
	static Scanner customerInput = new Scanner(System.in);
	static Choices choice = new Choices();

	public static void main(String[] args) throws Exception {

		storeProducts.put("milk", 13);
		storeProducts.put("bread", 13);
		storeProducts.put("sugar", 13);
		storeProducts.put("coffee", 13);
		storeProducts.put("toothpaste", 13);
		storeProducts.put("toothBrush", 13);

		out.println("\t====Welcome to ShoppingCart====\n");
		
		do {
			choice.options();
			customerChoice = choice.getChoice();
			switch (customerChoice) {
			case "1":
				out.println(storeProducts);
				out.println("display menu(yes/no)");
				customerChoice=choice.inputCheck();
				break;
			case "2":
				customerItems = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
				out.println("Enter your name :");
				customerName = customerInput.next();
				userItems();

				out.println("Customer :" + customerName + " \npurchased items");
				out.println(customerItems);
				
				userItems=customerName.concat((customerItems).toString());
				customersList.add(userItems);
				out.println("display menu(yes/no)");
				customerChoice=choice.inputCheck();
				break;
			case "3":
				
				out.println(customersList);
				out.println("display menu(yes/no)");
				customerChoice=choice.inputCheck();
				break;
			case "4":
				out.println("Thank You for Choosing Us....Visit Again");
				System.exit(0);
				break;
			default:
				out.println("enter valid input");
				out.println("display menu(yes/no)");
				customerChoice=choice.inputCheck();
				break;
			}
		} while (customerChoice.equalsIgnoreCase("yes"));
		customerInput.close();
	}

	public static void userItems() throws Exception {
		out.println("\ndo you want to purchase any item(yes/no)..The value is 'Case Sensitive'");
		String customerChoice = choice.inputCheck();
		
		do{
		switch (customerChoice.toLowerCase()) {
		case "yes":
			
			out.println("enter the item and quantity");
			item = customerInput.next();
			try {
				if (!customerItems.containsKey(item)) {
					if (storeProducts.containsKey(item)) {
						itemQuantity = customerInput.nextInt();
						 itemQuantity1=itemQuantity;
						availability = storeProducts.get(item);
						remain = availability;

						itemQuantityCheck(availability, itemQuantity);
					} else {
						throw new ProductNotFoundException();
					}
				} 
				else {
					itemQuantity = customerInput.nextInt();
					availability = storeProducts.get(item);
					
					if(itemQuantity>availability)
						itemQuantity=0;
					itemQuantity = itemQuantity + customerItems.get(item);
					itemQuantityCheck(availability, itemQuantity);
					
					customerItems.put(item, itemQuantity);
					storeProducts.put(item, availability);
				}
			} catch (Exception e) {
				
				System.out.println("Enter Valid Input(yes/no)");
				
				customerChoice=choice.inputCheck();
			}

			out.println("do you want to purchase any other item(yes/no)...The value is 'Case Sensitive'");
			
			customerChoice = customerInput.next();
			break;
		case "no": break;
		default:System.out.println("Enter Valid Input(yes/no)");
		        customerChoice = customerInput.next(); 
		        break;
		}}while(customerChoice.equalsIgnoreCase("yes"));

	}

	public static void itemQuantityCheck(int availability, int itemQuantity) {

		try {
			if (availability >= itemQuantity) {
				
				availability = availability - itemQuantity;
				remain = availability;
				customerItems.put(item, itemQuantity);
				storeProducts.put(item, availability);
			} else {
				
				storeProducts.put(item, availability);
				throw new Exception();
			}
		}

		catch (Exception e) {

			out.println("we have only " + remain + " packets ..Please order within the availability..");
			
			out.println("want to order again (yes/no)");
			customerChoice=choice.inputCheck();
			if (customerChoice.equals("yes")) {
				out.println("enter Quantity");
				 itemQuantity = customerInput.nextInt();
				 
				itemQuantityCheck(availability, itemQuantity);
				
				availability=availability-itemQuantity;
				customerItems.put(item, itemQuantity);
			    storeProducts.put(item, availability);
			}
				
			
		}
		
	}

}
