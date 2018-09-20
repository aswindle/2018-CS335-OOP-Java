/**
 * @File: TreasureCardFactory.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 11:13:40 AM
 */

import java.util.Random;

public class TreasureCardFactory implements CardFactory {
	Random rng = new Random();
	
	/**
	 * Create a new random Treasure
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public Card createCard() {
		int value = 100 + rng.nextInt(300);
		int combatAdvantage = 1 + rng.nextInt(5);
		return new TreasureCard("Treasure", value, combatAdvantage);
	}
}
