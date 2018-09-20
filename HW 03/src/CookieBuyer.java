
/**
 * Program: CookieBuyer.java
 * 
 * Purpose: Abstract class for people who get cookies (namely GirlScouts and Customers)
 * 
 * @author Alex Swindle, aswindle@email.arizona.edu
 */

public abstract class CookieBuyer {
	private String name;
	private Inventory cookies;
	final String[] cookieNames = { "Samoas", "Dosidos", "Thin Mints" };

	

	/**
	 * Constructor with cookie numbers
	 * 
	 * @param name
	 * @param samoaBoxes
	 * @param dosidoBoxes
	 * @param mintBoxes
	 */
	public CookieBuyer(String name, int samoaBoxes, int dosidoBoxes, int mintBoxes) {
		this.name = name;
		cookies = new Inventory(samoaBoxes, dosidoBoxes, mintBoxes);
	}

	/**
	 * Empty constructor that defaults to 0 cookies
	 * 
	 * @param name
	 */
	public CookieBuyer(String name) {
		this.name = name;
		cookies = new Inventory();
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Add cookies to the inventory
	 * 
	 * @param type:
	 *            0 for Samoas, 1 for Dosidos, 2 for Thin Mints
	 * @param number
	 *            of boxes to get from leader
	 */
	public void getCookies(int type, int number) {
		cookies.addCookies(type, number);
		System.out.println(String.format("%s got %d boxes of %s.", this.name, number, cookieNames[type]));
	}

	/**
	 * Remove cookies from the inventory
	 * 
	 * @param type
	 * @param number
	 * @return true if removed; false otherwise
	 */
	public boolean removeCookies(int type, int number) {
		return cookies.removeCookies(type, number);
	}

	/**
	 * Print the current inventory
	 */
	public void printInventory() {
		System.out.println(getName() + "'s current inventory:\n" + cookies + '\n');
	}

	

}
