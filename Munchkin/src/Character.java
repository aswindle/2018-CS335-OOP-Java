
/**
 * @File: Character.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 3:06:00 PM
 */
import java.util.ArrayList;

public class Character {
	private boolean alive;
	private int level;
	private String name;
	private int handSize;
	private ArrayList<Card> doorCards;
	private ArrayList<Card> treasureCards;
	// do we calculate combatStrength in the character (here), or in combat
	// function?
	// private int combatStrength;
	// the default race
	RaceCard defaultRaceCard = new RaceCard("Human");
	// the race of this object
	RaceCard equippedRace;

	// TODO implement ClassCards
	// the default class
	ClassCard defaultClassCard = new ClassCard("None");
	// the class of this object
	ClassCard equippedClass;

	// the default treasure item - a treasure card with zero value/power
	TreasureCard defaultTreasureCard = new TreasureCard("Treasure", 0, 0);
	// the equipped card for this object
	TreasureCard equippedItem;

	/**
	 * Constructor; defaults everything to the basic version
	 * 
	 * @param name
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public Character(String name) {
		alive = true;
		this.name = name;
		level = 1;
		// equip default race, class, item
		this.equip(defaultRaceCard);
		this.equip(defaultClassCard);
		this.equip(defaultTreasureCard);

		handSize = 5;
		doorCards = new ArrayList<Card>();
		treasureCards = new ArrayList<Card>();
	}

	/**
	 * method for equipping a card.
	 * 
	 * @author: Daniel Koblas
	 * @email : koblas@email.arizona.edu
	 *
	 * @param card
	 */
	public void equip(Card card) {
		Class<RaceCard> raceCardType = RaceCard.class;
		Class<ClassCard> classCardType = ClassCard.class;
		Class<TreasureCard> treasureCardType = TreasureCard.class;

		// check type of card passed and call appropriate type-specific equip
		// method
		if (raceCardType.isInstance(card)) {
			this.equipRace((RaceCard) card);
		}
		else if (classCardType.isInstance(card)) {
			this.equipClass((ClassCard) card);
		}
		else if (treasureCardType.isInstance(card)) {
			this.equipItem((TreasureCard) card);
		}
		else {
			throw new IllegalArgumentException("Cannot equip card of type " + card.getType());
		}
	}

	/**
	 * un-equips the passed item, setting back to default. performs validation
	 * to make sure the passed card was equipped first
	 * 
	 * @author: Daniel Koblas
	 * @email : koblas@email.arizona.edu
	 *
	 * @param card
	 */
	public void unEquip(Card card) {
		Class<RaceCard> raceCardType = RaceCard.class;
		Class<ClassCard> classCardType = ClassCard.class;
		Class<TreasureCard> treasureCardType = TreasureCard.class;

		// check type of card passed and call appropriate type-specific equip
		// method
		if (raceCardType.isInstance(card)) {
			// check that we are trying to unequip the card that is equipped
			// already
			// == operator is used to compare that these are same object
			if (equippedRace == card) {
				this.unEquipRace();
			}
			else {
				throw new IllegalArgumentException(card.getClass().getName() + " was not equipped.");
			}
		}
		else if (classCardType.isInstance(card)) {
			if (equippedClass == card) {
				this.unEquipClass();
			}
			else {
				throw new IllegalArgumentException(card.getClass().getName() + " was not equipped.");
			}
		}
		else if (treasureCardType.isInstance(card)) {
			if (equippedItem == card) {
				this.unEquipItem();
			}
			else {
				throw new IllegalArgumentException(card.getClass().getName() + " was not equipped.");
			}

		}
		else {
			throw new IllegalArgumentException("Unequip failed for reasons unknown.");
		}
	}

	/**
	 * equip the race card
	 * 
	 * @author: Daniel Koblas
	 * @email : koblas@email.arizona.edu
	 *
	 * @param raceCard
	 *            to equip
	 */
	public void equipRace(RaceCard raceCard) {
		this.setEquippedRace(raceCard);
	}

	/**
	 * equip new class card
	 * 
	 * @author: Daniel Koblas
	 * @email : koblas@email.arizona.edu
	 *
	 * @param classCard
	 *            to equip
	 */
	public void equipClass(ClassCard classCard) {
		this.setEquippedClass(classCard);
	}

	/**
	 * equip new item card
	 * 
	 * @author: Daniel Koblas
	 * @email : koblas@email.arizona.edu
	 *
	 * @param treasureCard
	 *            to equip
	 */
	public void equipItem(TreasureCard treasureCard) {
		this.setEquippedItem(treasureCard);
	}

	/**
	 * un-equips the current race by resetting it to the default
	 * 
	 * @author: Daniel Koblas
	 * @email : koblas@email.arizona.edu
	 *
	 */
	public void unEquipRace() {
		this.setEquippedRace(defaultRaceCard);
	}

	/**
	 * un-equips the current class by resetting it to default
	 * 
	 * @author: Daniel Koblas
	 * @email : koblas@email.arizona.edu
	 *
	 */
	public void unEquipClass() {
		this.setEquippedClass(defaultClassCard);
	}

	/**
	 * un-equips the current item by resetting it to default
	 * 
	 * @author: Daniel Koblas
	 * @email : koblas@email.arizona.edu
	 *
	 */
	public void unEquipItem() {
		this.setEquippedItem(defaultTreasureCard);
	}

	/**
	 * @return alive or not
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive
	 *            the alive to set
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @return the level
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Level up by 1
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void levelUp() {
		this.level++;
	}

	/**
	 * Lose 1 level
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void levelDown() {
		this.level--;
	}

	/**
	 * @return the name
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the currentRace
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public String getEquippedRace() {
		return equippedRace.getRace();
	}

	/**
	 * @param raceCard
	 *            the raceCard to set
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 * 
	 *        modified by Daniel Koblas changed to private; this is now called
	 *        by equipRace(...)
	 */
	private void setEquippedRace(RaceCard raceCard) {
		this.equippedRace = raceCard;
	}

	/**
	 * @return the equippedClass
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 * 
	 *        modified by Daniel Koblas refactored names, changed to call method
	 *        on object
	 */
	public String getEquippedClass() {
		return equippedClass.getCardClass();
	}

	/**
	 * @param classCard
	 *            the equippedClass to set
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 * 
	 *        modified by Daniel Koblas changed to private; this is now called
	 *        by equipClass(...)
	 */
	private void setEquippedClass(ClassCard classCard) {
		this.equippedClass = classCard;
	}

	/**
	 * @author: Daniel Koblas
	 * @email : koblas@email.arizona.edu
	 *
	 * @return currently equipped TreasureCard object
	 */
	public TreasureCard getEquippedItem() {
		return equippedItem;
	}

	/**
	 * @author: Daniel Koblas
	 * @email : koblas@email.arizona.edu
	 *
	 * @param treasureCard
	 *            the treasureCard to set.
	 */
	private void setEquippedItem(TreasureCard treasureCard) {
		this.equippedItem = treasureCard;
	}

	/**
	 * @return the handSize
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 * 
	 *        Modified by Daniel Koblas added check/modifier for dwarf race
	 */
	public int getHandSize() {
		// dwarfs hold an extra card
		if (equippedRace.getRace().equals("Dwarf")) {
			return handSize + 1;
		}
		else {
			return handSize;
		}
	}

	/**
	 * @param handSize
	 *            the handSize to set
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void setHandSize(int handSize) {
		this.handSize = handSize;
	}

	/**
	 * @return the doorCards
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public ArrayList<Card> getDoorCards() {
		return doorCards;
	}

	/**
	 * @param doorCards
	 *            the doorCards to set
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void setDoorCards(ArrayList<Card> doorCards) {
		this.doorCards = doorCards;
	}

	/**
	 * @return the treasureCards
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public ArrayList<Card> getTreasureCards() {
		return treasureCards;
	}

	/**
	 * Add cards to list of treasures
	 * 
	 * @param newTreasures
	 *            ArrayList of cards to add to current list of treasures
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void addTreasureCards(ArrayList<Card> newTreasures) {
		treasureCards.addAll(newTreasures);
	}

	/**
	 * Add cards to hand of door cards
	 * 
	 * @param newDoors
	 *            ArrayList of cards to add to current list of door cards
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void addDoorCards(ArrayList<Card> newDoors) {
		doorCards.addAll(newDoors);
	}

	/**
	 * Add a single new card to the hand of Door cards
	 * 
	 * @param newCard
	 *            the card to add
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void addDoorCard(Card newCard) {
		doorCards.add(newCard);
	}

	/**
	 * Add a single new card to the hand of Treasure cards
	 * 
	 * @param newCard
	 *            the card to add
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void addTreasureCard(Card newCard) {
		treasureCards.add(newCard);
	}

	/**
	 * Return how many Door cards are currently in the hand
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public int getDoorCardCount() {
		return doorCards.size();
	}

	/**
	 * Return how many Treasure cards are currently in the hand
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public int getTreasureCardCount() {
		return treasureCards.size();
	}

	/**
	 * Remove a particular card from the hand of Door cards
	 * 
	 * @param toRemove
	 *            the card to remove
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void removeDoorCard(Card toRemove) {
		doorCards.remove(toRemove);
	}

	/**
	 * Remove a list of cards
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void removeDoorCards(ArrayList<Card> toRemove) {
		for (Card c : toRemove) {
			removeDoorCard(c);
		}
	}

	/**
	 * Remove a particular card from the hand of Treasure cards
	 * 
	 * @param toRemove
	 *            the card to remove
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void removeTreasureCard(Card toRemove) {
		treasureCards.remove(toRemove);
	}

	/**
	 * Removes a list of cards
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void removeTreasureCards(ArrayList<Card> toRemove) {
		for (Card c : toRemove) {
			removeTreasureCard(c);
		}
	}

	/**
	 * @return whether the player has treasures left in their hand
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public boolean hasUnequippedTreasures() {
		return !treasureCards.isEmpty();
	}

	/**
	 * Empty the entire hand of Door cards
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void discardAll() {
		doorCards = new ArrayList<Card>();
	}

	/**
	 * Removes the highest-valued treasure card from the player
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void loseHighestTreasure() {
		// Can't lose a card if there aren't any current treasures
		if (!treasureCards.isEmpty()) {
			// Get the highest-valued card
			TreasureCard best = (TreasureCard) treasureCards.get(0);
			for (Card c : treasureCards) {
				if (((TreasureCard) c).getValue() > best.getCombatAdvantage()) {
					best = (TreasureCard) c;
				}
			}
			removeTreasureCard(best);
		}
	}

	/**
	 * @return the current combat strength of the player
	 * 
	 *         Needs to be updated to loop through all equipped items if we
	 *         allow more than one
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public int getStrength() {
		return level + equippedItem.getCombatAdvantage();
	}

	/**
	 * @return whether the player has any Monster cards in their hand of Door
	 *         cards
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public boolean hasMonster() {
		boolean result = false;
		for (Card c : doorCards) {
			if (c.getType().equals("Monster")) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * Prints the current state of the Character and all items
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void dumpStats() {
		System.out.println("Name: " + this.name);
		System.out.println("Alive: " + this.alive);
		System.out.println("Level: " + this.level);
		System.out.println("Current Race: " + this.getEquippedRace());
		System.out.println("Current Class: " + this.getEquippedClass());
		System.out.println("Hand Size: " + this.getHandSize());
		System.out.println("Current item: " + this.equippedItem.toString());
		System.out.println("Current Door Cards:");
		for (int i = 0; i < doorCards.size(); i++) {
			System.out.println("Card " + i + " " + doorCards.get(i).toString());
		}
		System.out.println("Current Treasure Cards:");
		for (int i = 0; i < treasureCards.size(); i++) {
			System.out.println("Card " + i + " " + treasureCards.get(i).toString());
		}
	}
}
