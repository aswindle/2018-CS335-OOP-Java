/**
 * Program: Customer.java
 * 
 * Purpose: models a Girl Scout cookie customer; also implements a version of
 * the Observer pattern, as Customers can be notified when their preferred
 * cookie type is updated if they're subscribed to that list
 * 
 * @author Alex Swindle, aswindle@email.arizona.edu
 */

public class Customer extends CookieBuyer {
	private int customerID;
	private String phone;
	private String address;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param customerID
	 * @param phone
	 * @param address
	 */
	public Customer(String name, int customerID, String phone, String address) {
		super(name);
		this.customerID = customerID;
		this.phone = phone;
		this.address = address;
	}

	/**
	 * @return the customerID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * Print the fields of the customer
	 */
	public String toString() {
		return "Customer " + getName() + ": customerID=" + customerID + ", phone=" + phone + ", address=" + address;
	}

	/**
	 * Implements the Observer pattern. Prints a message when it's updated,
	 * which is called by the GirlScout to which the Customer is subscribed
	 * 
	 * @param type
	 * @param scoutName
	 */
	public void update(int type, String scoutName) {
		System.out.println(getName() + " is excited about " + scoutName + "'s new boxes of " + cookieNames[type] + "!");
	}

}
