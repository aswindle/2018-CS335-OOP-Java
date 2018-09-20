
/**
 * Program: CookieClient.java
 * 
 * Purpose: Runs a menu-driven system for modeling Girl Scout cookie sales
 * 
 * @author Alex Swindle, aswindle@email.arizona.edu
 */

import java.util.Scanner;

public class CookieClient {

	static String MAINMENU = "Choose an action:\n1 Get Cookies\n2 Add Customer\n3 Send Cookies\n4 Print Inventory\n5 Print Customer List\n6 Print Customer Inventories\n7 Print Sales History\n8 Exit";
	static String COOKIEPROMPT = "Which type of cookie?\n0 Samoas\n1 Dosidos\n2 Thin Mints";

	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		GirlScout scout = new GirlScout("Greta", 0, 0, 0);
		
		// Hard-code 3 customers who will subscribe to changes in their preferred cookies
		Customer sam = new Customer("Sam", 100, "555-555-5555", "Drury Lane");
		Customer don = new Customer("Don", 200, "555-555-5556", "Fake St.");
		Customer thad = new Customer("Thad", 300, "555-555-5557", "Fake Ave");
		scout.addCustomer(sam);
		scout.addCustomer(don);
		scout.addCustomer(thad);
		scout.addListener(don, 1);
		scout.addListener(sam, 0);
		scout.addListener(thad, 2);
		scout.addListener(sam, 1);
		
		System.out.println("Greta the Girl Scout is selling cookies,\nthough she doesn't have any yet.\nShe has a few customers subscribed to changes already.");
		// Keep running until user chooses exit
		boolean finished = false;
		while (!finished) {
			
			// Get a valid menu item
			System.out.println(MAINMENU);
			String attempt = kb.next();
			while (!isValid(attempt, 1, 8)) {
				System.out.println(MAINMENU);
				attempt = kb.next();
			}
			int action = Integer.parseInt(attempt);

			/*
			 * Perform an action from the menu
			 */
			switch (action) {

			// Get cookies
			case 1:
				// Get a valid cookie type
				System.out.println(COOKIEPROMPT);
				attempt = kb.next();
				while (!isValid(attempt, 0, 2)) {
					System.out.println(COOKIEPROMPT);
					attempt = kb.next();
				}
				int cookieType = Integer.parseInt(attempt);

				// Get a valid number of boxes
				System.out.print("How many boxes? ");
				attempt = kb.next();
				while (!isValid(attempt, 1, 999)) {
					System.out.print("How many boxes? ");
					attempt = kb.next();
				}
				int getBoxes = Integer.parseInt(attempt);

				// Get the cookies
				scout.getCookies(cookieType, getBoxes);
				break;

			// Add customer
			case 2:
				// Get fields for Customer constructor
				System.out.print("Customer name: ");
				String newName = kb.next();
				
				// Customer ID must be an integer
				System.out.print("Customer ID: ");
				attempt = kb.next();
				while (!isValid(attempt, 0, 10000)) {
					System.out.println("Customer ID: ");
					attempt = kb.next();
				}
				int newID = Integer.parseInt(attempt);
				
				System.out.print("Phone: ");
				String newPhone = kb.next();
				System.out.print("Address: ");
				String newAddress = kb.next();
				scout.addCustomer(new Customer(newName, newID, newPhone, newAddress));
				break;

			// Send cookies
			case 3:
				// Get a valid cookie type
				System.out.println(COOKIEPROMPT);
				attempt = kb.next();
				while (!isValid(attempt, 0, 2)) {
					System.out.println(COOKIEPROMPT);
					attempt = kb.next();
				}
				cookieType = Integer.parseInt(attempt);

				// Get a valid number of boxes
				System.out.print("How many boxes? ");
				attempt = kb.next();
				while (!isValid(attempt, 1, 999)) {
					System.out.print("How many boxes? ");
					attempt = kb.next();
				}
				int sendBoxes = Integer.parseInt(attempt);

				// Get a valid customer ID
				System.out.print("Customer ID: ");
				attempt = kb.next();
				while (!isValid(attempt, 1, 10000)) {
					System.out.print("Customer ID: ");
					attempt = kb.next();
				}
				int sendID = Integer.parseInt(attempt);

				// Send the cookies
				scout.sendCookies(cookieType, sendID, sendBoxes);
				break;

			// Print inventory
			case 4:
				scout.printInventory();
				break;

			// Print customers
			case 5:
				scout.printCustomers();
				break;

			// Print customer inventories
			case 6:
				scout.printCustomerInventories();
				break;

			// Print sales history
			case 7:
				scout.printSales();
				break;

			// Exit
			case 8:
				finished = true;
				break;
			}
		}
		kb.close();
		System.exit(0);

	}

	/**
	 * Checks to see if a string can be converted to int between a lower and
	 * upper bound
	 * 
	 * @param attempt:
	 *            String to try to parse as an integer
	 * @param lowerBound:
	 *            smallest acceptable int
	 * @param upperBound:
	 *            largest acceptable int
	 * @return true if valid, false if not
	 */
	private static boolean isValid(String attempt, int lowerBound, int upperBound) {
		boolean valid = false;
		// Try to parse attempt as an int; if possible, check if it's between
		// lower and upper bounds
		try {
			int action = Integer.parseInt(attempt);
			if (action >= lowerBound && action <= upperBound) {
				valid = true;
			}
			else {
				System.out.println("Error: choice must be between  " + lowerBound + " and " + upperBound);
			}
		}
		catch (NumberFormatException e) {
			System.out.println("Error: enter an integer between " + lowerBound + " and " + upperBound);
		}
		return valid;
	}
}
