
/**
 * Abstract class for a deck of cards that both the DoorDeck and TreasureDeck will use
 * @File: Deck.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 10:37:06 AM
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public abstract class Deck {
	protected Random rng;
	protected ArrayList<Card> deck;

	public Deck() {
		rng = new Random();
		deck = new ArrayList<Card>();
	}

	public abstract void buildDeck();

	/**
	 * Draw an integer number of cards, and remove them from the deck
	 * 
	 * @param cardsToDraw:
	 *            the number of cards to draw
	 * @returns an ArrayList of cards from the deck
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public ArrayList<Card> draw(int cardsToDraw) {
		ArrayList<Card> retval = new ArrayList<Card>();
		for (int i = 0; i < cardsToDraw; i++) {
			// Remove from the deck, add to the return list
			retval.add(deck.remove(0));
		}

		return retval;
	}

	/**
	 * Shuffle the deck of cards
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void shuffle() {
		Stack<Card> tempStack = new Stack<Card>();

		while (!deck.isEmpty()) {
			// Randomly empty the deck into a Stack
			// Select a value from 0 to deck's size to remove, so no out of
			// bounds errors will occur
			tempStack.push(deck.remove(rng.nextInt(deck.size())));
		}

		// Pop the stack back into the deck
		while (!tempStack.isEmpty()) {
			deck.add(tempStack.pop());
		}
	}
	
	/**
	 * Print out contents of deck, one per line
	 */
	public String toString() {
		String retval = "";
		for(int i = 0; i<deck.size(); i++) {
			retval += "Card " + i + " " + deck.get(i).toString() + '\n';
		}
		return retval;
	}

}
