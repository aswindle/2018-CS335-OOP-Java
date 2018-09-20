
/**
 * @File: TreasureDeck.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 11:16:40 AM
 */
import java.util.Random;

public class TreasureDeck extends Deck {
	private CardFactory treasureFactory;

	public TreasureDeck() {
		super();
		treasureFactory = new TreasureCardFactory();

		// Build the deck
		buildDeck();

		// Shuffle the deck
		shuffle();
	}

	/**
	 * Create the deck with 100 cards to start
	 */
	public void buildDeck() {
		for (int i = 0; i < 100; i++) {
			deck.add(treasureFactory.createCard());
		}

	}

}
