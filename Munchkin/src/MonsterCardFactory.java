/**
 * Concrete factory for Monster cards
 * 
 * @File: MonsterCardFactory.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 7:40:31 AM
 */
import java.util.Random;

public class MonsterCardFactory implements CardFactory{
	Random rng = new Random();
	
	/**
	 * Create a Monster card with randomized stats
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 * 
	 * edited by Daniel Koblas
	 * 	added undead
	 */
	public Card createCard() {
		// Fields are all random numbers from 1-5, 1-10, or 1-20.
		// nextInt() goes from 0 to upper bound (exclusive), so add 1
		int level = 1 + rng.nextInt(20);
		int vulnerability = 1 + rng.nextInt(10);
		int goodStuff = 1 + rng.nextInt(5);
		int badStuff = 1 + rng.nextInt(5);
		// undead flag, let's say 1/3 chance of being undead
		boolean undead;
		if (1 + rng.nextInt(9) < 4) {
			undead = true;
		}
		else {
			undead = false;
		}
		return new MonsterCard("Monster", level, undead, vulnerability, goodStuff, badStuff);
	}
}
