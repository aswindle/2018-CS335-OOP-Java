
/*second version of munchkin gui
 *Author: Christian Marquez 
 *
 *changes made so far:
 *added four buttons (sellItem, equipItem, changeRace, changeClass
 *removed the pictures from the buttons, we cant use them anyways
 *some of the position of the buttons and labels were moved around
 *most of the action events here will be changed since they need to be hooked up to methods
 *
 *
 */
import javafx.application.Application; // JavaFX Application
import javafx.scene.text.Font; // needed for JavaFX Font object
import javafx.scene.text.FontWeight; // needed for JavaFX FontWeight object
import javafx.scene.text.Text; // needed for JavaFX Text object
import javafx.scene.Scene; // needed for JavaFX scene object
import javafx.scene.paint.Color; // needed for JavaFX color object
import javafx.geometry.Insets; // needed for JavaFX Insets object
import javafx.geometry.Pos; // needed for JavaFX Pos object
import javafx.event.ActionEvent; // needed for JavaFX ActionEvent
import javafx.event.EventHandler; // needed for JavaFX EventHandler
import javafx.scene.input.KeyEvent; // needed for JavaFX KeyEvent
import javafx.scene.input.KeyCode; // needed for JavaFX KeyCodes
import javafx.stage.Stage; // needed for JavaFX Stage object
import javafx.application.Platform; // needed for  Platform.exit()
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
// Needed for HBox object
// needed for JavaFX GridPane object
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
// needed for JavaFX Label object
// needed for JavaFX TextField control
// needed for JavaFX ComboBox control
// needed for JavaFX Button control
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.*;

import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.StringTokenizer;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class MunchkinGUI extends Application {

	private static final String imagePath = "file:images/";
	private static final double SCENE_WIDTH = 1400;
	private static final double SCENE_HEIGHT = 600;
	private static final double CARD_WIDTH = 100;
	private static final double CARD_HEIGHT = 120;

	// create labels, buttons, textbox, and Imageviews

	TextArea taFeedback;

	TextField tfGetPlayerName;

	Label playerName;
	Label playerLevel;
	Label playerClass;
	Label playerRace;
	Label playerGold;
	Label equippedItem;
	Label combatAdvantage;

	Button btnDone;
	Button btnDrawDoor;
	Button btnDrawTreasure;
	Button btnDiscard;
	Button btnLookForTrouble;
	Button btnLootTheRoom;

	// btnStartGame should get the player's name from the textfield and begin
	// the game
	// TODO: wire this up to work with the textfield tfGetPlayerName
	Button btnStartGame;

	// new buttons added for version 2 of the GUI below
	Button btnSellItem;
	Button btnEquipItem;
	Button btnChangeRace;
	Button btnChangeClass;
	Button btnRollDice;

	ToggleButton card1;
	ToggleButton card2;
	ToggleButton card3;
	ToggleButton card4;
	ToggleButton card5;
	ToggleButton card6;

	// debug mode buttons
	Button btnDebugElf;
	Button btnDebugDwarf;
	Button btnDebugHalfling;
	Button btnDebugHuman;
	Button btnDebugCleric;
	Button btnDebugWarrior;
	Button btnDebugWizard;
	Button btnDebugNoClass;

	HBox doorBox;
	HBox treasureBox;

	// debug card we configure to set in the card slot we select
	Card debugCard;

	// Actual game pieces
	DoorDeck doorDeck;
	TreasureDeck treasureDeck;
	Character player;
	Game game;

	ArrayList<Card> selectedDoorCards = new ArrayList<Card>();
	ArrayList<Card> selectedTreasureCards = new ArrayList<Card>();
	RaceCard equipRaceCard = null;
	ClassCard equipClassCard = null;
	int selectedTCards = 0;
	int i = 0;
	int totalTValue = 0;
	int purchasedLevels = 0;

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage myStage) {

		// enable debug mode interface
		boolean debugMode = true;

		// we will get this from a textfield
		String nameOfPlayer = "default name";

		// decks
		doorDeck = new DoorDeck();
		treasureDeck = new TreasureDeck();

		// the game object, we will create this later once we get the player's
		// name and create the player.
		player = new Character(nameOfPlayer);
		game = new Game(player, doorDeck, treasureDeck, this);
		// game.gameLoop();

		final String FONT_NAME = "Georgia";
		myStage.setTitle("Munchkin Game Gui");

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		// grid.setGridLinesVisible(true);

		Scene scene;
		if (debugMode) {
			// create scene with extra space for the debug UI
			scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT + 350);
		}
		else {
			scene = new Scene(grid, SCENE_WIDTH, SCENE_HEIGHT);
		}
		// Scene mainScene = new Scene(grid, 1300, 500);

		ImageView mapImg = new ImageView((imagePath + "MunchkinMap.png"));
		mapImg.setFitWidth(250);
		mapImg.setFitHeight(450);
		grid.add(mapImg, 0, 1, 2, 10);

		// set up the dice button
		btnRollDice = new Button();
		// arraylist of dice images
		ArrayList<ImageView> diceImages = new ArrayList<ImageView>();
		for (int i = 1; i < 7; i++) {
			ImageView dieImg = new ImageView((imagePath + Integer.toString(i) + ".png"));
			dieImg.setFitHeight(75);
			dieImg.setFitWidth(75);
			diceImages.add(dieImg);
		}
		// set to image 1 to begin.
		btnRollDice.setGraphic(diceImages.get(0));
		grid.add(btnRollDice, 7, 1);
		// wire up the dice button
		btnRollDice.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				// get roll
				int roll = roll();
				game.setDiceRoll(roll);
				btnRollDice.setGraphic(diceImages.get(roll - 1));
				// get the dice feedback from the game.
				setFeedback("You rolled a " + game.getDiceRoll());
				if (debugMode) {
					System.out.println(
							"dice clicked. roll was:" + roll + ". game object has rol of: " + game.getDiceRoll());
				}
			}
		});

		// creating text field that instructs player on what to do
		taFeedback = new TextArea();
		taFeedback.setPrefColumnCount(18);
		taFeedback.setPrefRowCount(5);
		grid.add(taFeedback, 8, 1, 11, 3);

		taFeedback.setText("Click Draw to draw from the door deck.\r\n" + "Click Done to skip drawing.\r\n" + "");

		// creating button that will end the players turn
		btnDone = new Button("Done");
		grid.add(btnDone, 8, 4);

		btnDone.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				taFeedback.setText("Ending turn...");
				// method that handles ending the turn
			}
		});

		// creating button that will draw a new card
		btnDrawDoor = new Button("Draw Door Card");
		grid.add(btnDrawDoor, 8, 5);

		// Rewired by Andre Hall
		btnDrawDoor.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				// method that handles drawing a new card
				getDrawnDoorCard();
				updateAll();
			}
		});
		
		// creating button that will draw a new card
		btnDrawTreasure = new Button("Draw Treasure Card");
		grid.add(btnDrawTreasure, 9, 5);

		// Rewired by Andre Hall
		btnDrawTreasure.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				// method that handles drawing a new card
				getDrawnTreasureCard();
				updateAll();
			}
		});
		
		btnLookForTrouble = new Button("Look For Trouble");
		grid.add(btnLookForTrouble, 8, 6);
		
		btnLookForTrouble.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				game.lookForTroublePhase();
			}
		});
		
		//creating button allowing to discard cards
		btnDiscard = new Button("Discard");
		grid.add(btnDiscard, 9, 4);
		
		btnDiscard.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				//method that handles deleteing card
				player.removeDoorCards(selectedDoorCards);
				player.removeTreasureCards(selectedTreasureCards);
				updateAll();
			}
		});
		

		// new butttons added for version 2 of the gui
		btnSellItem = new Button("   Sell   ");
		grid.add(btnSellItem, 4, 6);

		// Method stub filled in by Andre Hall
		btnSellItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				taFeedback.setText("Select an Item to sell");
				if (selectedDoorCards.get(0) == null) {
					taFeedback.setText("Please select an item to sell.");
				}
				else {
					selectedTCards = selectedTreasureCards.size();
					for (i = 0; i < selectedTCards; i++) {
						if (selectedTreasureCards.get(i).getType().compareTo("Treasure") == 0) {
							totalTValue = totalTValue + ((TreasureCard) selectedTreasureCards.get(i)).getValue();
						}
						else {
							taFeedback.setText("I don't want that junk! I only buy treasures.");
						}
					}

					purchasedLevels = (totalTValue - (totalTValue % 1000)) / 1000;
					for (i = 0; i < purchasedLevels; i++) {
						player.levelUp();
					}
					totalTValue = 0;
					purchasedLevels = 0;
				}
			}
		});

		btnEquipItem = new Button("Equip Item");
		grid.add(btnEquipItem, 4, 5);

		btnEquipItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				taFeedback.setText("Select an item to equip");
				// button method that handles equiping an item

			}
		});

		btnChangeRace = new Button("Change Race");
		grid.add(btnChangeRace, 3, 6);

		// Stub filled by Andre Hall
		btnChangeRace.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				if (selectedDoorCards.get(0) == null) {
					player.unEquipRace();
					taFeedback.setText("Original race restored.");
				}
				else {
					if (selectedDoorCards.get(1) != null) {
						taFeedback.setText("Please only select 1 card to change your race.");
					}
					else {
						if (selectedDoorCards.get(0).getType().compareTo("Race") == 0) {
							equipRaceCard = (RaceCard) selectedDoorCards.get(0);
							selectedDoorCards.remove(0);
							player.equipRace(equipRaceCard);
							taFeedback.setText("Race changed.");
						}
						else {
							taFeedback.setText("Please select a race card to change your race.");
						}
					}
				}
			}
		});

		btnChangeClass = new Button("Change Class");
		grid.add(btnChangeClass, 3, 5);

		// Stub filled by Andre Hall
		btnChangeClass.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				if (selectedDoorCards.get(0) == null) {
					player.unEquipClass();
					taFeedback.setText("Class removed.");
				}
				else {
					if (selectedDoorCards.get(1) != null) {
						taFeedback.setText("Please only select 1 card to change your class.");
					}
					else {
						if (selectedDoorCards.get(0).getType().compareTo("Class") == 0) {
							equipClassCard = (ClassCard) selectedDoorCards.get(0);
							selectedDoorCards.remove(0);
							player.equipClass(equipClassCard);
							taFeedback.setText("Race changed.");
						}
						else {
							taFeedback.setText("Please select a class card to change your class.");
						}
					}
				}
			}
		});

		// label displaying players name
		// this will appear after the player enters their name in the
		// tfGetPlayerName box and clicks the button.
		playerName = new Label("Player Name");
		playerName.setFont(Font.font(FONT_NAME, FontWeight.BOLD, 15));
		// Character creation...
		tfGetPlayerName = new TextField();
		tfGetPlayerName.setPrefColumnCount(20);
		tfGetPlayerName.setPromptText("Enter your name to create your character.");

		// button will set the new character object up with the player's name,
		// and switch this
		// field to display only the name label from here on out.
		Button btnPlayerName = new Button("Set Name");
		HBox hbPlayerName = new HBox();
		hbPlayerName.getChildren().addAll(tfGetPlayerName, btnPlayerName);
		btnPlayerName.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// name isn't empty
				if (!tfGetPlayerName.getText().equals("")) {
					player.setName(tfGetPlayerName.getText());
					grid.getChildren().remove(hbPlayerName);
					grid.add(playerName, 3, 0);
					updateAll();
				}
			}
		});
		grid.add(hbPlayerName, 3, 0);

		// the players level will be displayed after the :
		playerLevel = new Label("Player Level");
		grid.add(playerLevel, 3, 1);

		// players class will be displayed
		playerClass = new Label("Current Class: ");
		grid.add(playerClass, 3, 2);
		playerClass.setBorder(new Border(new BorderStroke(Color.rgb(80, 80, 160, 1.0), BorderStrokeStyle.SOLID,
				new CornerRadii(2.0), BorderStroke.MEDIUM)));

		// players race will be displayed
		playerRace = new Label("Current Race: ");
		grid.add(playerRace, 3, 3);
		playerRace.setBorder(new Border(new BorderStroke(Color.rgb(80, 80, 160, 1.0), BorderStrokeStyle.SOLID,
				new CornerRadii(2.0), BorderStroke.MEDIUM)));

		// label displaying the players current gold
		playerGold = new Label("Gold: ");
		grid.add(playerGold, 3, 4);
		
		//new labels for eq item and combat advan
		equippedItem = new Label("Equipped Item: ");
		grid.add(equippedItem, 4, 2);
		equippedItem.setBorder(new Border(new BorderStroke(Color.rgb(80, 80, 160, 1.0), BorderStrokeStyle.SOLID,
				new CornerRadii(2.0), BorderStroke.MEDIUM)));
		
		combatAdvantage = new Label("Combat Advantage: ");
		grid.add(combatAdvantage, 4, 3);
		combatAdvantage.setBorder(new Border(new BorderStroke(Color.rgb(80, 80, 160, 1.0), BorderStrokeStyle.SOLID,
				new CornerRadii(2.0), BorderStroke.MEDIUM)));

		/**
		 * Final stage/scene setup
		 */
		if (debugMode) {
			GridPane gpDebugPane = debugPanel();
			grid.add(gpDebugPane, 0, 14, 10, 10);
		}
		doorBox = new HBox();
		treasureBox = new HBox();
		player.addDoorCards(doorDeck.draw(4));
		player.addTreasureCards(treasureDeck.draw(4));
		updateAll();
		grid.add(doorBox, 3, 10, 10, 1);
		grid.add(treasureBox, 3, 11, 10, 1);
		myStage.setScene(scene);
		myStage.show();
	}

	/**
	 * Updates the player info labels with current fields
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void updateLabels() {
		playerName.setText("Player Name: " + player.getName());
		playerLevel.setText("Player Level: " + player.getLevel());
		playerClass.setText("Player Class: " + player.getEquippedClass());
		playerRace.setText("Player Race: " + player.getEquippedRace());
		playerGold.setText("Current Gold in Hand: " + updateTreasureButtons());
		equippedItem.setText("Equipped Item: "+ player.getEquippedItem());
		combatAdvantage.setText("Combat Advantage: "+ player.getStrength());
	}

	/**
	 * Updates the HBox holding buttons for the DoorCards, one for each Door
	 * card currently in the hand; also updates the list of selected cards each
	 * time one is clicked
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void updateDoorButtons() {
		doorBox.getChildren().clear();
		ArrayList<Card> doorCards = player.getDoorCards();
		ArrayList<CardButton> doorButtons = new ArrayList<>();
		for (int i = 0; i < doorCards.size(); i++) {
			CardButton button = new CardButton(doorCards.get(i));
			button.setPrefHeight(CARD_HEIGHT);
			button.setPrefWidth(CARD_WIDTH);

			// wire the buttons to do something
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (button.isSelected()) {
						selectedDoorCards.add(button.getCard());
						System.out.println("button " + button.getCard().toString() + " pushed.");
						// add card to arraylist
					}
					else {
						selectedDoorCards.remove(button.getCard());
						System.out.println("button " + button.getCard().toString() + " un-pushed.");
						// remove card from arraylist
					}
				}
			});
			doorButtons.add(button);
		}
		doorBox.getChildren().addAll(doorButtons);
	}

	/**
	 * Returns list of currently selected Door cards
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public ArrayList<Card> getSelectedDoorCards() {
		return this.selectedDoorCards;
	}

	/**
	 * Updates the HBox of TreasureCards with CardButtons, one for each Treasure
	 * card currently in the hand; also updates the list of selected cards and
	 * returns the current total value of the treasures
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public int updateTreasureButtons() {
		int total = 0;
		treasureBox.getChildren().clear();
		ArrayList<Card> treasureCards = player.getTreasureCards();
		ArrayList<CardButton> treasureButtons = new ArrayList<>();
		for (int i = 0; i < treasureCards.size(); i++) {
			total += ((TreasureCard) treasureCards.get(i)).getValue();
			CardButton button = new CardButton(treasureCards.get(i));
			button.setPrefHeight(CARD_HEIGHT);
			button.setPrefWidth(CARD_WIDTH);

			// wire the buttons to do something
			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (button.isSelected()) {
						selectedTreasureCards.add(button.getCard());
						System.out.println("button " + button.getCard().toString() + " pushed.");
					}
					else {
						selectedTreasureCards.remove(button.getCard());
						System.out.println("button " + button.getCard().toString() + " un-pushed.");
					}
				}
			});

			treasureButtons.add(button);
		}
		treasureBox.getChildren().addAll(treasureButtons);
		return total;
	}

	/**
	 * Returns list of currently selected Treasure cards
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public ArrayList<Card> getSelectedTreasureCards() {
		return this.selectedTreasureCards;
	}

	/**
	 * Calls all of the update methods
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void updateAll() {
		updateTreasureButtons();
		updateDoorButtons();
		updateLabels();
	}

	/**
	 * Returns one card drawn from the Door Deck; prints card info in feedback
	 * field.
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public Card getDrawnDoorCard() {
		Card drawn = doorDeck.draw(1).get(0);
		setFeedback("Drawn Card:\n" + drawn.toString());
		player.addDoorCard(drawn);
		updateAll();
		return drawn;
	}
	
	/**
	 * Returns one card drawn from the Treasure Deck; prints card info in feedback
	 * field.
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public Card getDrawnTreasureCard() {
		Card drawn = treasureDeck.draw(1).get(0);
		setFeedback("Drawn Card:\n" + drawn.toString());
		player.addTreasureCard(drawn);
		updateAll();
		return drawn;
	}
	
	

	/**
	 * updated the feedback textarea with the given string
	 * 
	 * @author: Daniel Koblas
	 * @email : koblas@email.arizona.edu
	 *
	 * @param feedBack
	 */
	public void setFeedback(String feedBack) {
		taFeedback.setText(feedBack);
	}

	/**
	 * rolls the dice, returns an int.
	 * 
	 * @author: Daniel Koblas
	 * @email : koblas@email.arizona.edu
	 *
	 * @return
	 */
	public int roll() {
		int roll;
		Random roller = new Random();
		roll = roller.nextInt(6) + 1;

		return roll;
	}

	public GridPane debugPanel() {
		/**
		 * debug mode gridpane. This will have the buttons that set the various
		 * properties of the character on demand for testing purposes.
		 * 
		 * @author Daniel Koblas
		 */
		GridPane gpDebugPane = new GridPane();
		// race debug panel
		VBox vbDebugRaces = new VBox();
		VBox vbDebugClasses = new VBox();
		Label lblDebugRaces = new Label("Set Race:");
		btnDebugElf = new Button("Elf");
		btnDebugDwarf = new Button("Dwarf");
		btnDebugHalfling = new Button("Halfling");
		btnDebugHuman = new Button("Human");
		vbDebugRaces.getChildren().addAll(lblDebugRaces, btnDebugElf, btnDebugDwarf, btnDebugHalfling, btnDebugHuman);
		/**
		 * buttons will set the character's current race to the race of the
		 * button by equipping that card
		 */
		btnDebugElf.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// generate an elf card
				RaceCard race = (RaceCard) new RaceCardFactory().createCard("Elf");
				// equip that card on the character
				player.equipRace(race);
				System.out.println("Race set to: " + player.getEquippedRace());
				updateAll();
			}
		});
		btnDebugDwarf.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// generate an dwarf card
				RaceCard race = (RaceCard) new RaceCardFactory().createCard("Dwarf");
				// equip that card on the character
				player.equipRace(race);
				System.out.println("Race set to: " + player.getEquippedRace());
				updateAll();
			}
		});
		btnDebugHalfling.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// generate an halfling card
				RaceCard race = (RaceCard) new RaceCardFactory().createCard("Halfling");
				// equip that card on the character
				player.equipRace(race);
				System.out.println("Race set to: " + player.getEquippedRace());
				updateAll();
			}
		});
		btnDebugHuman.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// generate a human card
				RaceCard race = (RaceCard) new RaceCardFactory().createCard("Human");
				// equip that card on the character
				player.equipRace(race);
				System.out.println("Race set to: " + player.getEquippedRace());
				updateAll();
			}
		});

		// Class debug panel
		Label lblDebugClasses = new Label("Set Class:");
		btnDebugWarrior = new Button("Warrior");
		btnDebugWizard = new Button("Wizard");
		btnDebugCleric = new Button("Cleric");
		btnDebugNoClass = new Button("No Class");
		vbDebugClasses.getChildren().addAll(lblDebugClasses, btnDebugWarrior, btnDebugWizard, btnDebugCleric,
				btnDebugNoClass);
		/**
		 * buttons will set the character's current race to the race of the
		 * button by equipping that card
		 */
		btnDebugWarrior.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// generate an warrior card
				ClassCard classCard = (ClassCard) new ClassCardFactory().createCard("Warrior");
				// equip that card on the character
				player.equipClass(classCard);
				System.out.println("Class set to: " + player.getEquippedClass());
				updateAll();
			}
		});
		btnDebugWizard.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// generate an wizard card
				ClassCard classCard = (ClassCard) new ClassCardFactory().createCard("Wizard");
				// equip that card on the character
				player.equipClass(classCard);
				System.out.println("Class set to: " + player.getEquippedClass());
				updateAll();
			}
		});
		btnDebugCleric.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// generate a cleric card
				ClassCard classCard = (ClassCard) new ClassCardFactory().createCard("Cleric");
				// equip that card on the character
				player.equipClass(classCard);
				System.out.println("Class set to: " + player.getEquippedClass());
				updateAll();
			}
		});
		btnDebugNoClass.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// generate a "no class" card
				ClassCard classCard = (ClassCard) new ClassCardFactory().createCard("None");
				// equip that card on the character
				player.equipClass(classCard);
				System.out.println("Class set to: " + player.getEquippedClass());
				updateAll();
			}
		});

		// gridpane to allow setting a specific card to a card of a set type
		// with specific values
		Button btnSetCard = new Button("Set card");
		// radiobuttons for card numbers
		RadioButton rbSlot1 = new RadioButton("Slot 1");
		RadioButton rbSlot2 = new RadioButton("Slot 2");
		RadioButton rbSlot3 = new RadioButton("Slot 3");
		RadioButton rbSlot4 = new RadioButton("Slot 4");
		RadioButton rbSlot5 = new RadioButton("Slot 5");
		RadioButton rbSlot6 = new RadioButton("Slot 6");
		// togglegroup for the slots
		ToggleGroup tgDebugCardSlots = new ToggleGroup();
		rbSlot1.setToggleGroup(tgDebugCardSlots);
		rbSlot2.setToggleGroup(tgDebugCardSlots);
		rbSlot3.setToggleGroup(tgDebugCardSlots);
		rbSlot4.setToggleGroup(tgDebugCardSlots);
		rbSlot5.setToggleGroup(tgDebugCardSlots);
		rbSlot6.setToggleGroup(tgDebugCardSlots);

		VBox vbSlotRadios = new VBox();
		vbSlotRadios.setPrefWidth(75);
		vbSlotRadios.getChildren().addAll(btnSetCard, rbSlot1, rbSlot2, rbSlot3, rbSlot4, rbSlot5, rbSlot6);

		// for the monster card
		RadioButton rbMonsterCard = new RadioButton("Monster");
		rbMonsterCard.setPrefWidth(100);
		// checkbox for undead
		CheckBox cbUndead = new CheckBox("undead");
		cbUndead.setPrefWidth(100);
		// combobox for ints
		ObservableList<Integer> dbMonsterLevels = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11,
				12, 13, 14, 15, 16, 17, 18, 19, 20);
		ComboBox<Integer> cbDebugMonsterLevel = new ComboBox<Integer>(dbMonsterLevels);
		cbDebugMonsterLevel.setEditable(true);
		cbDebugMonsterLevel.setPrefWidth(100);
		cbDebugMonsterLevel.getSelectionModel().selectFirst();
		ObservableList<Integer> dbMonsterVulns = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		ComboBox<Integer> cbDebugMonsterVulns = new ComboBox<Integer>(dbMonsterVulns);
		cbDebugMonsterVulns.setEditable(true);
		cbDebugMonsterVulns.setPrefWidth(100);
		cbDebugMonsterVulns.getSelectionModel().selectFirst();
		ObservableList<Integer> dbMonsterGoods = FXCollections.observableArrayList(1, 2, 3, 4, 5);
		ComboBox<Integer> cbDebugMonsterGoods = new ComboBox<Integer>(dbMonsterGoods);
		cbDebugMonsterGoods.setEditable(true);
		cbDebugMonsterGoods.setPrefWidth(100);
		cbDebugMonsterGoods.getSelectionModel().selectFirst();
		ObservableList<Integer> dbMonsterBads = FXCollections.observableArrayList(1, 2, 3, 4, 5);
		ComboBox<Integer> cbDebugMonsterBads = new ComboBox<Integer>(dbMonsterBads);
		cbDebugMonsterBads.setEditable(true);
		cbDebugMonsterBads.setPrefWidth(100);
		cbDebugMonsterBads.getSelectionModel().selectFirst();
		// Holder for all these monster options
		GridPane gpMonsterOptions = new GridPane();
		gpMonsterOptions.add(new Label("Level"), 2, 0);
		gpMonsterOptions.add(new Label("Vulnerability:"), 3, 0);
		gpMonsterOptions.add(new Label("GoodStuff:"), 4, 0);
		gpMonsterOptions.add(new Label("BadStuff:"), 5, 0);
		gpMonsterOptions.add(rbMonsterCard, 0, 1);
		gpMonsterOptions.add(cbUndead, 1, 1);
		gpMonsterOptions.add(cbDebugMonsterLevel, 2, 1);
		gpMonsterOptions.add(cbDebugMonsterVulns, 3, 1);
		gpMonsterOptions.add(cbDebugMonsterGoods, 4, 1);
		gpMonsterOptions.add(cbDebugMonsterBads, 5, 1);

		// curse card settings
		RadioButton rbCurseCard = new RadioButton("Curse");
		ObservableList<Integer> dbCurseNumber = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		ComboBox<Integer> cbDebugCurseNumber = new ComboBox<Integer>(dbCurseNumber);
		cbDebugCurseNumber.setEditable(true);
		cbDebugCurseNumber.setPrefWidth(100);
		cbDebugCurseNumber.getSelectionModel().selectFirst();
		// holder for the curse card options
		GridPane gpCurseOptions = new GridPane();
		gpCurseOptions.add(rbCurseCard, 0, 1);
		gpCurseOptions.add(new Label("Number:"), 1, 0);
		gpCurseOptions.add(cbDebugCurseNumber, 1, 1);

		// treasure card options
		RadioButton rbTreasureCard = new RadioButton("Treasure");
		ObservableList<Integer> dbTreasureValue = FXCollections.observableArrayList(100, 200, 300, 400);
		ComboBox<Integer> cbTreasureValue = new ComboBox<Integer>(dbTreasureValue);
		cbTreasureValue.setEditable(true);
		cbTreasureValue.setPrefWidth(100);
		cbTreasureValue.getSelectionModel().selectFirst();
		ObservableList<Integer> dbTreasureCombatAdv = FXCollections.observableArrayList(1, 2, 3, 4, 5);
		ComboBox<Integer> cbTreasureCombatAdv = new ComboBox<Integer>(dbTreasureCombatAdv);
		cbTreasureCombatAdv.setEditable(true);
		cbTreasureCombatAdv.setPrefWidth(100);
		cbTreasureCombatAdv.getSelectionModel().selectFirst();
		// holder for treasure card options
		GridPane gpTreasureOptions = new GridPane();
		gpTreasureOptions.add(rbTreasureCard, 0, 1);
		gpTreasureOptions.add(new Label("Value:"), 1, 0);
		gpTreasureOptions.add(new Label("Comb. Adv.:"), 2, 0);
		gpTreasureOptions.add(cbTreasureValue, 1, 1);
		gpTreasureOptions.add(cbTreasureCombatAdv, 2, 1);

		// helpful card options
		RadioButton rbHelpfulCard = new RadioButton("Helpful");
		ObservableList<Integer> dbHelpfulValue = FXCollections.observableArrayList(100, 200, 300, 400);
		ComboBox<Integer> cbHelpfulValue = new ComboBox<Integer>(dbHelpfulValue);
		cbHelpfulValue.setEditable(true);
		cbHelpfulValue.setPrefWidth(100);
		cbHelpfulValue.getSelectionModel().selectFirst();
		ObservableList<Integer> dbHelpfulCombatAdv = FXCollections.observableArrayList(1, 2, 3, 4, 5);
		ComboBox<Integer> cbHelpfulCombatAdv = new ComboBox<Integer>(dbHelpfulCombatAdv);
		cbHelpfulCombatAdv.setEditable(true);
		cbHelpfulCombatAdv.setPrefWidth(100);
		cbHelpfulCombatAdv.getSelectionModel().selectFirst();
		// holder for treasure card options
		GridPane gpHelpfulOptions = new GridPane();
		gpHelpfulOptions.add(rbHelpfulCard, 0, 1);
		gpHelpfulOptions.add(new Label("Value:"), 1, 0);
		gpHelpfulOptions.add(new Label("Comb. Adv.:"), 2, 0);
		gpHelpfulOptions.add(cbHelpfulValue, 1, 1);
		gpHelpfulOptions.add(cbHelpfulCombatAdv, 2, 1);

		// create a set race card
		RadioButton rbRaceCard = new RadioButton("Race");
		ObservableList<String> dbRaceOptions = FXCollections.observableArrayList("Elf", "Dwarf", "Halfling", "Human");
		ComboBox<String> cbRaceOptions = new ComboBox<String>(dbRaceOptions);
		cbRaceOptions.getSelectionModel().selectFirst();
		GridPane gpRaceOptions = new GridPane();
		gpRaceOptions.add(rbRaceCard, 0, 0);
		gpRaceOptions.add(cbRaceOptions, 1, 0);

		// create a set class card
		RadioButton rbClassCard = new RadioButton("Class");
		ObservableList<String> dbClassOptions = FXCollections.observableArrayList("Warrior", "Wizard", "Cleric",
				"None");
		ComboBox<String> cbClassOptions = new ComboBox<String>(dbClassOptions);
		cbClassOptions.setPrefWidth(100);
		cbClassOptions.getSelectionModel().selectFirst();
		GridPane gpClassOptions = new GridPane();
		gpClassOptions.add(rbClassCard, 0, 0);
		gpClassOptions.add(cbClassOptions, 1, 0);

		// togglegroup for the card options radio buttons
		ToggleGroup tgCardOptions = new ToggleGroup();
		rbMonsterCard.setToggleGroup(tgCardOptions);
		rbCurseCard.setToggleGroup(tgCardOptions);
		rbTreasureCard.setToggleGroup(tgCardOptions);
		rbHelpfulCard.setToggleGroup(tgCardOptions);
		rbRaceCard.setToggleGroup(tgCardOptions);
		rbClassCard.setToggleGroup(tgCardOptions);

		// gridpane holding the debug controls
		gpDebugPane.add(vbDebugRaces, 0, 0, 1, 8);
		gpDebugPane.add(vbDebugClasses, 1, 0, 1, 8);
		gpDebugPane.add(vbSlotRadios, 2, 0, 1, 8);
		gpDebugPane.add(gpMonsterOptions, 3, 0);
		gpDebugPane.add(gpCurseOptions, 3, 1);
		gpDebugPane.add(gpTreasureOptions, 3, 2);
		gpDebugPane.add(gpHelpfulOptions, 3, 3);
		gpDebugPane.add(gpRaceOptions, 3, 4);
		gpDebugPane.add(gpClassOptions, 3, 5);

		/**
		 * configure the set card button.
		 * 
		 * @author Daniel Koblas Here we set the debug card, getting values that
		 *         we configured in the debug UI controls. TODO: give some
		 *         functionality to setting these cards to the appropriate card
		 *         slots in the GUI
		 */
		btnSetCard.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				// get the selected option from the radiobutton togglegroup
				if (tgCardOptions.getSelectedToggle() == rbMonsterCard) {
					// create this card
					debugCard = new MonsterCard("Monster", cbDebugMonsterLevel.getValue(), cbUndead.isSelected(),
							cbDebugMonsterVulns.getValue(), cbDebugMonsterGoods.getValue(),
							cbDebugMonsterBads.getValue());
					// TODO: set this card to the card slot
					// something something tgDebugCardSlots.getSelected() == ...
					System.out.println("Card set to: " + debugCard.toString());
				}
				else if (tgCardOptions.getSelectedToggle() == rbCurseCard) {
					debugCard = new CurseCard("Curse", cbDebugCurseNumber.getValue());
					// TODO: set this card to the card slot

					System.out.println("Card set to: " + debugCard.toString());
				}
				else if (tgCardOptions.getSelectedToggle() == rbTreasureCard) {
					debugCard = new TreasureCard("Treasure", cbTreasureValue.getValue(),
							cbTreasureCombatAdv.getValue());
					// TODO: set this card to the card slot

					System.out.println("Card set to: " + debugCard.toString());
				}
				else if (tgCardOptions.getSelectedToggle() == rbHelpfulCard) {
					debugCard = new HelpfulCard("Helpful", cbHelpfulValue.getValue(), cbHelpfulCombatAdv.getValue());
					// TODO: set this card to the card slot

					System.out.println("Card set to: " + debugCard.toString());
				}
				else if (tgCardOptions.getSelectedToggle() == rbRaceCard) {
					debugCard = new RaceCard("Race", cbRaceOptions.getValue());
					// TODO: set this card to the card slot

					System.out.println("Card set to: " + debugCard.toString());
				}
				else if (tgCardOptions.getSelectedToggle() == rbClassCard) {
					debugCard = new ClassCard("Class", cbClassOptions.getValue());
					// TODO: set this card to the card slot

					System.out.println("Card set to: " + debugCard.toString());
				}
				else if (tgCardOptions.getSelectedToggle() == null) {
					System.out.println("Select a card type.");
				}
				else {
					throw new IllegalArgumentException("Something went wrong with the debug card");
				}

			}
		});

		return gpDebugPane;
	}
}