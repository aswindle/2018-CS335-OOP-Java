
/**
 * Program: GirlScout.java
 * 
 * Purpose: models a Girl Scout's cookie sales; 
 * 	also implements a version of the Observer pattern, 
 * 	as it maintains a list of customers interested in each type
 * 	of cookie and notifies them when that type is updated
 * 
 * @author Alex Swindle, aswindle@email.arizona.edu
 */

import java.util.ArrayList;

public class GirlScout extends CookieBuyer {
	private ArrayList<Customer> customers;
	private int[] boxesSold;

	// A listthat will contain the three cookie subscription lists
	private ArrayList<ArrayList<Customer>> listenerLists;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param samoaBoxes
	 * @param dosidoBoxes
	 * @param mintBoxes
	 */
	public GirlScout(String name, int samoaBoxes, int dosidoBoxes, int mintBoxes) {
		super(name, samoaBoxes, dosidoBoxes, mintBoxes);
		customers = new ArrayList<Customer>();
		boxesSold = new int[] { 0, 0, 0 };

		// Add 3 subscription lists:
		// Position 0: Samoas
		// Position 1: Dosidos
		// Position 2: Thin Mints
		listenerLists = new ArrayList<ArrayList<Customer>>();
		for (int i = 0; i < 3; i++) {
			listenerLists.add(new ArrayList<Customer>());
		}
	}

	/**
	 * Add a new customer
	 * 
	 * @param newCustomer
	 */
	public void addCustomer(Customer newCustomer) {
		customers.add(newCustomer);
	}

	/**
	 * Print the list of customers
	 */
	public void printCustomers() {
		System.out.println(customers);
	}

	/**
	 * Print how many of each box have been sold so far
	 */
	public void printSales() {
		System.out.println(String.format("Sales so far:\n%s: %d\n%s: %d\n%s: %d", "Samoas", boxesSold[0], "Dosidos",
				boxesSold[1], "Thin Mints", boxesSold[2]));
	}

	/**
	 * Get a particular customer by ID number
	 * 
	 * @param custID
	 * @return the customer that matches the ID; null if not found
	 */
	private Customer getCustomer(int custID) {
		Customer result = null;
		for (Customer customer : customers) {
			if (customer.getCustomerID() == custID) {
				result = customer;
			}
		}
		return result;
	}

	/**
	 * Print the inventories of all current customers
	 */
	public void printCustomerInventories() {
		for (Customer customer : customers) {
			customer.printInventory();
		}
	}

	/**
	 * Send cookies to a customer
	 * 
	 * @param type:
	 *            0 for Samoas, 1 for Dosidos, 2 for Thin Mints
	 * @param customer:
	 *            customer ID number
	 * @param boxes:
	 *            number of boxes to send
	 */
	public void sendCookies(int type, int customerID, int boxes) {
		Customer recipient = getCustomer(customerID);
		// Found the customer
		if (recipient != null) {
			// Only update sales if the removal was successful (resulted in
			// nonnegative boxes)
			if (removeCookies(type, boxes)) {
				// Update sales for that type
				boxesSold[type] += boxes;
				System.out.println(String.format("%s sent %d boxes of %s to %s.", getName(), boxes, cookieNames[type],
						recipient.getName()));
				recipient.getCookies(type, boxes);
			}
			else {
				System.out.println(
						String.format("Insufficient boxes of %s. Get more from the leader.", cookieNames[type]));
			}
		}
		// Didn't find the customer
		else {
			System.out.println("Invalid customer ID. No cookies sent.");
		}
	}

	/*
	 * Methods to implement the Observer pattern:
	 * 
	 * Override the getCookies method to update listeners of that type
	 * 
	 * Add listeners
	 * 
	 * Remove listeners
	 * 
	 * Method that actually updates the listeners
	 */

	/**
	 * Overrides the getCookies method of the superclass. Updates the cookies as
	 * normal, but also notifies the listeners of each cookie type
	 */
	@Override
	public void getCookies(int type, int number) {
		super.getCookies(type, number);
		updateListeners(type);
	}

	/**
	 * Add a customer to the subscription list for a type of cookie
	 * 
	 * @param customer
	 * @param cookieType:
	 *            0 Samoas, 1 Dosidos, 2 Thin Mints
	 */
	public void addListener(Customer customer, int cookieType) {
		listenerLists.get(cookieType).add(customer);
	}

	/**
	 * Remove a customer from the subscription list for a type of cookie
	 * 
	 * @param customer
	 * @param cookieType
	 */
	public void removeListener(Customer customer, int cookieType) {
		listenerLists.get(cookieType).remove(customer);
	}

	/**
	 * Call the update method on every listener of a given cookie type
	 * 
	 * @param type:
	 *            0 for Samoas, 1 for Dosidos, 2 for Thin Mints
	 */
	public void updateListeners(int type) {
		for (Customer customer : listenerLists.get(type)) {
			customer.update(type, getName());
		}
	}
}
