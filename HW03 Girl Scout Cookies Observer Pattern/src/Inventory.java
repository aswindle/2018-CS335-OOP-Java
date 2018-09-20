/**
 * Program: Inventory.java
 * 
 * Purpose: models a Girl Scout's cookie inventory
 * 
 * @author Alex Swindle, aswindle@email.arizona.edu
 */

public class Inventory {
	private int[] cookies;

	/**
	 * Constructor
	 * 
	 * @param samoaBoxes
	 * @param dosidoBoxes
	 * @param thinMintsBoxes
	 */
	public Inventory(int samoaBoxes, int dosidoBoxes, int thinMintsBoxes) {
		cookies = new int[] { samoaBoxes, dosidoBoxes, thinMintsBoxes };
	}

	/**
	 * Empty constructor
	 */
	public Inventory() {
		cookies = new int[] { 0, 0, 0 };
	}

	/**
	 * Add cookies to the inventory
	 * 
	 * @param type:
	 *            0 for Samoas, 1 for Dosido, 2 for Thin Mints
	 * @param boxes:
	 *            number of boxes to add
	 */
	public void addCookies(int type, int boxes) {
		cookies[type] += boxes;
	}

	/**
	 * Remove cookies if possible
	 * 
	 * @param type:
	 *            0 for Samoas, 1 for Dosidos, 2 for Thin Mints
	 * @param boxes:
	 *            how many to remove
	 * @return true if remove was successful, false otherwise
	 */
	public boolean removeCookies(int type, int boxes) {
		boolean result = false;
		if (cookies[type] >= boxes) {
			cookies[type] -= boxes;
			result = true;
		}
		return result;
	}

	/**
	 * Prints the fields
	 */
	public String toString() {
		return String.format("Samoas: %d\nDosidos: %d\nThin Mints: %d", cookies[0], cookies[1], cookies[2]);
	}

}
