/**
 * Abstract class that all card types will extend
 * 
 * @File: Card.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 7:28:50 AM
 */

public abstract class Card {
	private String type;
	
	public Card(String type) {
		this.type = type;
	}
	
	/**
	 * Getter for the card type
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Simple toString()
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public String toString() {
		return this.type + " Card";
	}
}
