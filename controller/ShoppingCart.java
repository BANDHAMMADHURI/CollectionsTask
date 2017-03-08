package com.full.shopping;

import static java.lang.System.out;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ShoppingCart {
	static int remain;
	static String customerCartId, customerName, item, option;
	static int availability;
	static String customerChoice,input,userItems;
	static Integer itemQuantity/*,itemQuantity1*/;
	static Map<String, Integer> customerItems;
	static List<String> customersList=new LinkedList<String>();
 	//static Map<String, String> customer = new LinkedHashMap<String, String>();
	static Map<String, Integer> storeProducts = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
	//static Map<String, Map<String, Integer>> customerDetails = new LinkedHashMap<String, Map<String, Integer>>();
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
		//out.println("\t\tChoose your option\n");
		do {
			choice.options();
			customerChoice = choice.getChoice();
			switch (customerChoice) {
			case "1":
				out.println(storeProducts);
				out.println("display menu(yes/no)");
				input=choice.inputCheck();
				break;
			case "2":
				customerItems = new TreeMap<String, Integer>(String.CASE_INSENSITIVE_ORDER);
				out.println("Enter your name :");
				customerName = customerInput.next();
				userItems();

				out.println("Customer :" + customerName + " \npurchased items");
				out.println(customerItems);
				//customerDetails.put(customerName, customerItems);
				userItems=customerName.concat((customerItems).toString());
				customersList.add(userItems);
				out.println("display menu(yes/no)");
				input=choice.inputCheck();
				break;
			case "3":
				//out.println(customerDetails);
				out.println(customersList);
				out.println("display menu(yes/no)");
				input=choice.inputCheck();
				break;
			case "4":
				out.println("Thank You for Choosing Us....Visit Again");
				System.exit(0);
				break;
			default:
				out.println("enter valid input");
				out.println("display menu(yes/no)");
				input=choice.inputCheck();
				break;
			}
		} while (input.equalsIgnoreCase("yes"));
		customerInput.close();
	}

	public static void userItems() throws Exception {
		out.println("\ndo you want to purchase any item(yes/no)..The value is 'Case Sensitive'");
		String input = choice.inputCheck();
		// while (input.equalsIgnoreCase("yes")) {
		do{
		switch (input.toLowerCase()) {
		case "yes":
			/*do
			{*/
			out.println("enter the item and quantity");
			item = customerInput.next();
			try {
				if (!customerItems.containsKey(item)) {
					if (storeProducts.containsKey(item)) {
						itemQuantity = customerInput.nextInt();
						 //itemQuantity1=itemQuantity;
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
					//itemQuantityCheck(availability, itemQuantity);
					if(itemQuantity>availability)
						itemQuantity=0;
					itemQuantity = itemQuantity + customerItems.get(item);
					itemQuantityCheck(availability, itemQuantity);
					//itemQuantity = itemQuantity + customerItems.get(item);
					customerItems.put(item, itemQuantity);
					storeProducts.put(item, availability);
				}
			} catch (Exception e) {
				
				System.out.println("Enter Valid Input(yes/no)");
				
				input=choice.inputCheck();
			}

			out.println("do you want to purchase any other item(yes/no)...The value is 'Case Sensitive'");
			//input = choice.inputCheck();
			input = customerInput.next();
			break;
		case "no": break;
		default:System.out.println("Enter Valid Input(yes/no)");
		        input = customerInput.next(); 
		        break;
		}}while(input.equalsIgnoreCase("yes"));

	}

	public static void itemQuantityCheck(int availability, int itemQuantity) {

		try {
			if (availability >= itemQuantity) {
				
				availability = availability - itemQuantity;
				remain = availability;
				customerItems.put(item, itemQuantity);
				storeProducts.put(item, availability);
			} else {
				//itemQuantity=itemQuantity - /*itemQuantity1*/;
				//customerItems.put(item, itemQuantity);
				storeProducts.put(item, availability);
				throw new Exception();
			}
		}

		catch (Exception e) {

			out.println("we have only " + remain + " packets ..Please order within the availability..");
			//itemQuantity = itemQuantity - customerItems.get(item);
			out.println("want to order again (yes/no)");
			input=choice.inputCheck();
			if (input.equals("yes")) {
				out.println("enter Quantity");
				 itemQuantity = customerInput.nextInt();
				 
				itemQuantityCheck(availability, itemQuantity);
				//itemQuantity = itemQuantity + customerItems.get(item);
				if(availability<itemQuantity)
					itemQuantity=0;
				availability=availability-itemQuantity;
				customerItems.put(item, itemQuantity);
			    storeProducts.put(item, availability);
			}
				
			
		}
		
	}

}
