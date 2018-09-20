/**
 * @File: Helpful.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 10:21:44 AM
 */

public class HelpfulCard extends TreasureCard {
	/**
	 * Helpful card is exactly like a Treasure, but the fields are worth double
	 * 
	 * @param type
	 * @param value
	 * @param combatAdvantage
	 */
	public HelpfulCard(String type, int value, int combatAdvantage) {
		super(type, 2 * value, 2 * combatAdvantage);
	}
}
