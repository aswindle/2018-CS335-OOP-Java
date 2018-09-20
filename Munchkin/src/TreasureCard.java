/**
 * @File: Treasure.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 10:18:58 AM
 */

public class TreasureCard extends Card {
	private int value;
	private int combatAdvantage;

	/**
	 * Constructor
	 * 
	 * @param type:
	 *            "Treasure"
	 * @param value:
	 *            sale value
	 * @param combatAdvantage
	 */
	public TreasureCard(String type, int value, int combatAdvantage) {
		super(type);
		this.value = value;
		this.combatAdvantage = combatAdvantage;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the combatAdvantage
	 */
	public int getCombatAdvantage() {
		return combatAdvantage;
	}
	
	/**
	 * Prints out all of the data on a Treasure card
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	@Override
	public String toString() {
		return super.toString() + String.format("\nValue: %d\nAdvantage: %d", value, combatAdvantage);
	}

}
