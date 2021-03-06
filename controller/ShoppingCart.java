package com.full.shopping;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShoppingCart {

	static String customerName, item;
	static int availability;
	static String customerInput, userTotalItems;
	static Integer itemQuantity, quantity;
	static Map<String, Integer> customerItems;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<String> customersList = new LinkedList<String>();
	static Map<String, Integer> storeProducts = new LinkedHashMap<String, Integer>();
	static Choices choice = new Choices();

	public static void main(String[] args) throws Exception {

		storeProducts.put("milk", 13);
		storeProducts.put("bread", 13);
		storeProducts.put("sugar", 13);
		storeProducts.put("coffee", 13);
		storeProducts.put("lays", 13);

		out.println("\t====Welcome to ShoppingCart====\n");
		do {
			choice.options();
			customerInput = choice.getChoice();
			switch (customerInput) {
			case "1":
				out.println(storeProducts);
				out.println("display menu(yes/no)");
				customerInput = choice.inputCheck();
				break;
			case "2":
				customerItems = new LinkedHashMap<String, Integer>();
				nameCheck();
				userItems();
				if (!customerItems.isEmpty()) {
					out.println("Customer :" + customerName + " \npurchased items");
					out.println(customerItems);
					out.println("Thank You for Choosing Us....Visit Again");
					userTotalItems = customerName.concat((customerItems).toString());
					customersList.add(userTotalItems);
				}
				out.println("display menu(yes/no)");
				customerInput = choice.inputCheck();
				break;
			case "3":
				out.println(customersList);
				out.println("display menu(yes/no)");
				customerInput = choice.inputCheck();
				break;
			case "4":
				out.println("Thank You for Choosing Us....Visit Again");
				System.exit(0);
				break;
			default:
				out.println("enter valid input");
				out.println("display menu(yes/no)");
				customerInput = choice.inputCheck();
				break;
			}
		} while (customerInput.equalsIgnoreCase("yes"));
		br.close();
	}

	public static void userItems() throws Exception {
		out.println("\n" + storeProducts);
		out.println("\ndo you want to purchase any item(yes/no)");
		customerInput = choice.inputCheck();
		do {
			switch (customerInput.toLowerCase()) {
			case "yes":
				out.println("enter the item ");
				item = br.readLine().toLowerCase();
				try {
					if (!customerItems.containsKey(item)) {
						if (storeProducts.containsKey(item)) {
							quantityMatchCheck();

							availability = storeProducts.get(item);
							itemQuantityCheck(availability, itemQuantity);
						} else {
							throw new ProductNotFoundException();
						}
					} else {
						quantity = customerItems.get(item);
						availability = storeProducts.get(item);

						quantityMatchCheck();
						itemQuantityCheck(availability, itemQuantity);

						if (availability < itemQuantity)
							itemQuantity = 0;
						availability = availability - itemQuantity;
						itemQuantity = itemQuantity + quantity;
						customerItems.put(item, itemQuantity);
						storeProducts.put(item, availability);

					}
				} catch (Exception e) {

					System.out.println("Enter Valid Input(yes/no)");

					customerInput = choice.inputCheck();
				}
				out.println("do you want to purchase any other item(yes/no)");
				customerInput = choice.inputCheck();
				break;

			case "no":
				break;
			default:
				System.out.println("Enter Valid Input(yes/no)");
				customerInput = choice.inputCheck();
				break;
			}
		} while (customerInput.equalsIgnoreCase("yes"));
	}

	public static void itemQuantityCheck(int availability, int itemQuantity) throws Exception {

		try {
			if ((itemQuantity != 0)) {
			if ((availability >= itemQuantity)) {

				availability = availability - itemQuantity;
				
					storeProducts.put(item, availability);
					customerItems.put(item, itemQuantity);
			} else {

				storeProducts.put(item, availability);
				throw new Exception();
			}}
		}
		catch (Exception e) {
			out.println("out of stock\n");

			userItems();
		}
	}

	public static void nameCheck() throws IOException {
		boolean b = false;
		do {
			out.println("Enter your name :");
			customerName = br.readLine();
			for (int i = 0; i < customerName.length(); i++) {
				b = Character.isLetter(customerName.charAt(i));
				if (b)
					continue;
				else {
					out.println("enter in string format only");
					break;
				}
			}
		} while (!b);
	}
	public static void quantityMatchCheck() throws IOException {
		do {
			customerInput = "no";
			try {
				out.println("enter quantity");
				checkInteger(itemQuantity);
			} catch (ZeroQuantityException zqe) {
				out.println("quantity should not zero");
				out.println("do you want to enter again...(yes/no)");
				customerInput = choice.inputCheck();
			} catch (Exception e) {
				out.println("enter positive digits only");
				out.println("do you want to enter again...(yes/no)");
				customerInput = choice.inputCheck();
			}

		} while (customerInput.equals("yes"));
	}
	public static void checkInteger(Integer quantity) throws Exception {

		itemQuantity = Integer.parseInt(br.readLine());

		if (itemQuantity == 0)
			throw new ZeroQuantityException();
		if (itemQuantity < 0) {
			throw new Exception();
		}
	}
}
