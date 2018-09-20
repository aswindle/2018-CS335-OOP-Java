/**
 * @file: RaceCardFactory.java
 *
 * Purpose: Concrete factory for Race cards
 * 
 * @author: Daniel Koblas
 * @email : koblas@email.arizona.edu
 * 
 * @date: Feb 21, 2018 12:55:19 PM
 */

/**
 *
 *
 * @author: Daniel Koblas
 * @email : koblas@email.arizona.edu
 *
 */
public class RaceCardFactory implements CardFactory {


	public Card createCard(String raceName) {
		return new RaceCard("Race", raceName);
	}

	/* (non-Javadoc)
	 * @see CardFactory#createCard()
	 */
	@Override
	public Card createCard() {
		// must be passed a card type to get a card here.
		throw new IllegalArgumentException("Race card factory requires race to be specified (i.e. createCard\"Elf\").");
	}

}
