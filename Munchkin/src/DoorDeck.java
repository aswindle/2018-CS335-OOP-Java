
/**
 * The Door Deck for the Munchkin game
 * 
 * @File: DoorDeck.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 7:45:53 AM
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class DoorDeck extends Deck {
	// Factories
	private CardFactory monsterFactory;
	private CardFactory curseFactory;
	private CardFactory helpfulFactory;
	private RaceCardFactory raceFactory;
	private ClassCardFactory classFactory;

	public DoorDeck() {
		super();
		monsterFactory = new MonsterCardFactory();
		curseFactory = new CurseCardFactory();
		helpfulFactory = new HelpfulCardFactory();
		raceFactory = new RaceCardFactory();
		classFactory = new ClassCardFactory();

		// Build the deck
		buildDeck();

		// Shuffle the deck
		shuffle();
	}

	/**
	 * Build the 100 cards needed for the DoorDeck using the various Factories
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void buildDeck() {
		// 30 monsters
		for (int i = 0; i < 30; i++) {
			deck.add(monsterFactory.createCard());
		}
		// 30 curses
		for (int i = 0; i < 30; i++) {
			deck.add(curseFactory.createCard());
		}
		// 15 Race
		for (int i = 0; i < 5; i++) {
			deck.add(raceFactory.createCard("Elf"));
			deck.add(raceFactory.createCard("Halfling"));
			deck.add(raceFactory.createCard("Dwarf"));
		}

		// 15 Class
		for (int i = 0; i < 5; i++) {
			deck.add(classFactory.createCard("Warrior"));
			deck.add(classFactory.createCard("Wizard"));
			deck.add(classFactory.createCard("Cleric"));
		}

		// 10 Helpful
		for (int i = 0; i < 10; i++) {
			deck.add(helpfulFactory.createCard());
		}
	}
}
