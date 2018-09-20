/**
 * @File: HelpfulCardFactory.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 10:23:05 AM
 */

import java.util.Random;
public class HelpfulCardFactory implements CardFactory{
	Random rng = new Random();
	
	/**
	 * Create a new random Helpful card
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public Card createCard() {
		//Treasures have a value from 100 to 400, advantage from 1 to 5
		int value = 100 + rng.nextInt(300);
		int combatAdvantage = 1 + rng.nextInt(5);
		return new HelpfulCard("Helpful", value, combatAdvantage);
	}
}
