import java.util.ArrayList;

/**
 * @File: Game.java
 * 
 *        Second attempt at a main game loop, this time broken into phases
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 27, 2018
 * @Last modified: 5:49:17 PM
 */

public class Game {

	private Character player;
	private DoorDeck dDeck;
	private TreasureDeck tDeck;
	private int diceRoll;
	private MunchkinGUI gui;

	/**
	 * Constructor
	 * 
	 * @param player
	 * @param dDeck
	 * @param tDeck
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public Game(Character player, DoorDeck dDeck, TreasureDeck tDeck, MunchkinGUI gui) {
		this.player = player;
		this.dDeck = dDeck;
		this.tDeck = tDeck;
		this.gui = gui;
	}

	/**
	 * Main game loop: executes turns until the game is over
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void gameLoop() {
		// Game begins with player drawing 4 cards from each deck
		player.addDoorCards(dDeck.draw(4));
		player.addTreasureCards(tDeck.draw(4));

		while (!gameOver()) {
			turn();
		}
		if (gameWon()) {
			System.out.println("You win!");
		}
		else {
			System.out.println("You lost.");
		}
	}

	/**
	 * @return if the game is over
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public boolean gameOver() {
		return (!player.isAlive() || player.getLevel() >= 10);
	}

	/**
	 * @return if the player won
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public boolean gameWon() {
		return player.getLevel() >= 10 && player.isAlive();
	}

	/**
	 * Perform one game turn
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void turn() {
		// Draw a card, deal with monsters, curses, or others
		Card drawn = gui.getDrawnDoorCard();
		boolean monsterFought = drawPhase(drawn);

		// If we didn't fight a monster in the draw phase, look for trouble or
		// loot
		if (!monsterFought) {
			gui.setFeedback("Select a Monster and then click Look for Trouble\nor\nclick Loot the Room");
		}

		// TODO: Miscellaneous actions go here somehow?

		// Discard if needed; end the turn
		discardPhase();
		return;
	}

	/**
	 * Draw phase: monster, curse, or something else
	 * 
	 * @return whether or not a monster was fought in this phase
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public boolean drawPhase(Card drawn) {
		boolean monsterFought = false;

		// Card drawn = dDeck.draw(1).get(0);

		// Monster
		if (drawn.getType().equals("Monster")) {
			Combat fight = new Combat(player, (MonsterCard) drawn, tDeck, gui);
			fight.resolve();
			monsterFought = true;
		}

		// Curse
		else if (drawn.getType().equals("Curse")) {
			cursePhase((CurseCard) drawn);
		}

		// Anything else. Helpful cards go to Treasures hand,
		// all others go to Door hand
		else {
			if (drawn.getType().equals("Helpful")) {
				player.addTreasureCard(drawn);
			}
			else {
				player.addDoorCard(drawn);
			}
		}

		return monsterFought;
	}

	/**
	 * Curse card functionality
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void cursePhase(CurseCard curse) {
		int curseNumber = curse.getCurseNumber();
		switch (curseNumber) {
		// Lose 1 card
		case 1:
			Card selectedTreasure = gui.getSelectedTreasureCards().get(0);
			player.removeTreasureCard(selectedTreasure);
			break;
		// Lose 2 cards
		case 2:
			ArrayList<Card> selectedTreasures = gui.getSelectedTreasureCards();
			for (Card c : selectedTreasures) {
				player.removeTreasureCard(c);
			}
			break;
		// Lose race
		case 3:
			player.equipRace(player.defaultRaceCard);
			break;
		// Lose race, get new one
		case 4:
		case 5:
			RaceCard newRace = (RaceCard) gui.getSelectedDoorCards().get(0);
			player.equipRace(newRace);
			break;
		// Lose class
		case 6:
		case 7:
			player.equipClass(player.defaultClassCard);
			break;

		// Lose a level
		case 8:
		case 9:
		case 10:
			player.levelDown();
			break;
		}
	}

	/**
	 * Fight a selected monster card in the look for trouble phase
	 * 
	 * @param selectedMonster
	 *            the monster to fight
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void lookForTroublePhase() {
		ArrayList<Card> currentDoors =gui.getSelectedDoorCards();
		if(currentDoors.size()==1 && currentDoors.get(0).getType().equals("Monster")) {
			Combat fight = new Combat(player, (MonsterCard)currentDoors.get(0), tDeck, gui);
			fight.resolve();
		}
		
	}

	/**
	 * Loot the room: get a card from the door deck and add it to your hand
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void lootTheRoomPhase() {
		player.addDoorCards(dDeck.draw(1));
	}

	/**
	 * Pass cards between the GUI and game
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public ArrayList<Card> passCards(ArrayList<Card> cards) {
		return cards;
	}

	/**
	 * Sell a certain number of treasure cards from the hand in order to level
	 * up
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void sellPhase() {
		int total = 0;
		ArrayList<Card> cardsToSell = gui.getSelectedTreasureCards();
		// This will remove the cards from the hand as they're totaled up
		for (Card c : cardsToSell) {
			total += ((TreasureCard) c).getValue();
			player.removeTreasureCard(c);
		}
		// Have to have sold >1000 gold; can't use it to get to lvl 10
		if (total >= 1000 && player.getLevel() < 9) {
			player.levelUp();
		}
	}

	public void equipPhase() {

	}

	/**
	 * Discard cards if needed. Requires GUI implementation
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void discardPhase() {
		if (player.getDoorCardCount() > player.getHandSize()) {
			ArrayList<Card> discardList = gui.getSelectedDoorCards();
			for (Card c : discardList) {
				player.removeDoorCard(c);
			}
		}
	}

	/**
	 * @return the diceRoll
	 */
	public int getDiceRoll() {
		return diceRoll;
	}

	/**
	 * @param diceRoll
	 *            the diceRoll to set
	 */
	public void setDiceRoll(int diceRoll) {
		this.diceRoll = diceRoll;
	}
	// test

}
