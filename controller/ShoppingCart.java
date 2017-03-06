package com.full.shopping.controller;

import static java.lang.System.out;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.full.shopping.view.Choices;

public class ShoppingCart {
	static int remain;
	static String customerName, item;
	static int cartId, availability;
	static int customerChoice;
	static Integer itemQuantity;
	static Map<String, Integer> customerItems = new LinkedHashMap<String, Integer>();
	static Map<String, Integer> storeProducts = new LinkedHashMap<String, Integer>();
	static Map<String, Map<String, Integer>> customerDetails = new LinkedHashMap<String, Map<String, Integer>>();
	static Scanner customerInput = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		storeProducts.put("milk", 13);
		storeProducts.put("bread", 13);
		storeProducts.put("sugar", 13);
		storeProducts.put("coffee", 13);
		storeProducts.put("toothpaste", 13);
		storeProducts.put("toothBrush", 13);
		Choices choice = new Choices();
		out.println("\t====Welcome to ShoppingCart====\n");
		out.println("\t\tChoose your option\n");
		do {
			choice.options();
			customerChoice = choice.getChoice();

			switch (customerChoice) {
			case 1:
				do {
					customerItems.clear();
					out.println(storeProducts);
					userItems();
					out.println("Enter your Name:");
					customerName = customerInput.next();
					// cartId=customerInput.nextInt();
					out.println("Customer :" + customerName + " \npurchased items");
					out.println(customerItems);
					customerDetails.put(customerName, customerItems);
					out.println("any other customer(yes/anything)");
				} while (customerInput.next().equalsIgnoreCase("yes"));
				out.println("display menu(yes/anything)");
				break;
			case 2:
				out.println(customerDetails);
				out.println("display menu(yes/anything)");
				break;
			case 3:
				out.println("Thank You....Visit Again");
				System.exit(0);
				break;
			default:
				out.println("enter valid input");
				out.println("display menu(yes/anything)");
				break;
			}
		} while (customerInput.next().equalsIgnoreCase("yes"));
		customerInput.close();
	}

	public static void userItems() throws ProductNotFoundException {
		out.println("\ndo you want to purchase any item(yes/anything)");
		while (customerInput.next().equalsIgnoreCase("yes")) {

			out.println("enter the items and quantity");
			item = customerInput.next();
			if (!customerItems.containsKey(item)) {
				if (storeProducts.containsKey(item)) {
					itemQuantity = customerInput.nextInt();
					availability = storeProducts.get(item);
					remain=availability;
					/*try {
						if (availability >= itemQuantity) {
							availability = availability - itemQuantity;
							customerItems.put(item, itemQuantity);
							storeProducts.put(item, availability);
						}
						else {
							throw new Exception();
						}
					}

					catch (Exception e) {
						out.println(
								"we have only " + availability + " packets ..Please order within the availability..");
						out.println("want to order(1/any other digit)");
						if (customerInput.nextInt() == 1) {
							itemQuantity = customerInput.nextInt();
							storeProducts.put(item, availability);
						}
					}
*/
					itemQuantityCheck(availability, itemQuantity);
				} else {
					throw new ProductNotFoundException();
				}
			} else {
				itemQuantity = customerInput.nextInt();
				itemQuantity = itemQuantity + customerItems.get(item);
				itemQuantityCheck(availability,itemQuantity);
				customerItems.put(item, itemQuantity);
			}

			out.println("do you want to purchase any other item(yes/anything)");
		}

	}
       public static void itemQuantityCheck(int availability,int itemQuantity)
       {
    	   
    	   try {
				if (availability >= itemQuantity) {
					availability = availability - itemQuantity;
					remain=availability;
					customerItems.put(item, itemQuantity);
					storeProducts.put(item, availability);
				}
				else {
					throw new Exception();
				}
			}

			catch (Exception e) {

				out.println(
						"we have only " + remain + " packets ..Please order within the availability..");
				out.println("want to order(1/any other digit)");
				if (customerInput.nextInt() == 1) {
					out.println("enter Quantity");
					itemQuantity = customerInput.nextInt();
					
					itemQuantityCheck(availability,itemQuantity);
					customerItems.put(item,itemQuantity);
					storeProducts.put(item, availability);
				}
			}

       }
}
