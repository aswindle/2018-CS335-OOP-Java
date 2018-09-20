
/**
 * @File: Combat.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 24, 2018
 * @Last modified: 6:55:54 PM
 */
import java.util.Random;

public class Combat {
	private Character player;
	private MonsterCard monster;
	private TreasureDeck treasures;
	private MunchkinGUI gui;

	/**
	 * Constructor
	 * 
	 * @param player
	 * @param monster
	 * @param treasures
	 *            the deck to draw from if successful
	 */
	public Combat(Character player, MonsterCard monster, TreasureDeck treasures, MunchkinGUI gui) {
		this.player = player;
		this.monster = monster;
		this.treasures = treasures;
		this.gui = gui;
	}

	/**
	 * Resolve the combat. Collects the various pieces and checks.
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void resolve() {
		// Check to see if any items are going to be equipped
		boolean playerWon = false;
		if (player.hasUnequippedTreasures()) {
			// Equip them through the GUI
			int cardsPlayed = 0;
			int combatBoost = 0;
			// Warrior boost and (cleric + undead) boost work the same way
			if (player.getEquippedClass().equals("Warrior")
					|| (player.getEquippedClass().equals("Cleric") && monster.isUndead())) {
				combatBoost = cardsPlayed;
			}
			if (playerWins(combatBoost)) {
				player.levelUp();
				player.addTreasureCards(treasures.draw(monster.getGoodStuff()));
				playerWon = true;
			}
		}
		// TODO: currently the Wizard can't do this unless he doesn't have
		// unequipped items. How do we choose?

		// Discard all cards for the Wizard
		else if (player.getEquippedClass().equals("Wizard") && player.getDoorCardCount() > 0) {
			// TODO: Prompt from the GUI if the player is a wizard and can
			// discard
			// Combat resolves with no level up
			player.discardAll();
			player.addTreasureCards(treasures.draw(monster.getGoodStuff()));
			playerWon = true;
		}
		// Lost combat. Attempt to run away
		if (!playerWon) {
			// Roll the die
			Random die = new Random();
			int roll = 1 + die.nextInt(6);

			// Check for any bonuses on rolls
			String playerRace = player.getEquippedRace();
			String playerClass = player.getEquippedClass();
			// Elf gets boost of 1
			if (playerRace.equals("Elf")) {
				roll++;
			}
			// Halfling can reroll failures
			else if (playerRace.equals("Halfling") && roll < 5) {
				roll = 1 + die.nextInt(6);
			}
			// Wizard can discard
			if (playerClass.equals("Wizard") && player.getDoorCardCount() > 0) {
				int discards = 0;
				// TODO: discard cards from GUI
				roll += discards;
			}

			// Resolve die roll

			// Success
			if (roll > 4) {
				// Successfully ran away. Nothing else needed.
			}
			// Failed to run away; bad stuff happens
			else {
				int badStuff = monster.getBadStuff();
				switch (badStuff) {
				case 1:
					player.levelDown();
					break;
				case 2:
					player.levelDown();
					player.levelDown();
					break;
				case 3:
					player.loseHighestTreasure();
					break;
				case 4:
					// get 2 cards from the treasures hand from the player
					if (player.getTreasureCardCount() > 1) {
						Card card1 = player.getTreasureCards().get(0);
						Card card2 = player.getTreasureCards().get(1);
						player.removeTreasureCard(card1);
						player.removeTreasureCard(card2);
					}
					else if (player.getTreasureCardCount() > 0) {
						Card card1 = player.getTreasureCards().get(0);
						player.removeTreasureCard(card1);
					}
					else {
						// no cards, nothing happens here.
					}


					break;
				case 5:
					player.setAlive(false);
				}
			}
		}

	}

	/**
	 * Returns whether the player wins with current strength
	 * 
	 * @param boost:
	 *            combat advantage from discards to add to the player's strength
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public boolean playerWins(int boost) {
		int playerStrength = boost + player.getStrength();
		int monsterStrength = monster.getLevel();

		// Update Monster strength based on vulnerability value
		int vulnerability = monster.getVulnerability();
		switch (vulnerability) {
		case 1:
			if (player.getEquippedRace().equals("Elf")) {
				monsterStrength++;
			}
			break;
		case 2:
			if (player.getEquippedRace().equals("Halfling")) {
				monsterStrength++;
			}
			break;
		case 3:
			if (player.getEquippedRace().equals("Dwarf")) {
				monsterStrength++;
			}
			break;
		// Anything greater than 3
		default:
			monsterStrength++;
			break;
		}

		boolean result;
		// Actually compare the two
		// Win
		if (playerStrength > monsterStrength) {
			result = true;
		}
		// Tie if warrior
		else if (playerStrength == monsterStrength && player.getClass().getTypeName().equals("Warrior")) {
			result = true;
		}
		// Loss
		else {
			result = false;
		}
		return result;
	}

}
