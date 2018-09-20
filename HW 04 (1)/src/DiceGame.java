
/**
 * Program: DiceGame.java
 * 
 * Purpose: simple GUI-based dice betting game
 * 	Added functionality: 
 * 	* Both the text field and Roll Dice button can be triggered with Enter key
 * 	* Reset Stats button clears win/loss record
 * 
 * @author Alex Swindle, aswindle@email.arizona.edu
 */

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.util.Random;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class DiceGame extends Application {
	// Wins and losses are updated with a helper method, thus need to be class
	// members
	int wins = 0;
	int losses = 0;
	Label winStats;
	Label lossStats;

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage diceStage) {
		/*
		 * Create all of the pieces for the game
		 * 
		 * 1. Utility items: random number generator, backgrounds that will be
		 * used multiple times
		 * 
		 * 2. GUI container elements: stage title, root node, boxes for elements
		 * 
		 * 3. Individual pieces of the scene: dice ImageViews, buttons, labels,
		 * text field
		 */

		/*
		 * 1. Utility items
		 */
		Random rng = new Random();
		Background red = new Background(new BackgroundFill(Color.INDIANRED, null, null));
		Background white = new Background(new BackgroundFill(Color.WHITE, null, null));

		/*
		 * 2. GUI container elements
		 */
		diceStage.setTitle("Dice Game");

		VBox rootNode = new VBox(10);
		rootNode.setAlignment(Pos.CENTER);

		Scene diceScene = new Scene(rootNode, 400, 250);
		diceStage.setScene(diceScene);

		HBox diceRow = new HBox(10);
		diceRow.setPrefSize(400, 60);
		diceRow.setAlignment(Pos.CENTER);

		HBox buttonRow = new HBox(10);
		buttonRow.setMaxWidth(1000);
		buttonRow.setAlignment(Pos.CENTER);

		HBox statsRow = new HBox(10);
		statsRow.setAlignment(Pos.CENTER);

		/*
		 * 3. Individual pieces of the scene
		 */
		// Create the dice images; default to show 1
		ImageView viewL = new ImageView(new Image("1.png"));
		viewL.setFitWidth(100);
		viewL.setPreserveRatio(true);
		ImageView viewR = new ImageView(new Image("1.png"));
		viewR.setFitWidth(100);
		viewR.setPreserveRatio(true);

		// Create various labels and buttons, set their properties
		Button exit = new Button("Exit");
		Button rollDice = new Button("Roll Dice");
		Button resetStats = new Button("Reset Stats");

		Label betLabel = new Label("Your bet:");

		Label results = new Label("");
		results.setPrefWidth(300);
		results.setBorder(new Border(
				new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(3.0), BorderStroke.THIN)));
		results.setAlignment(Pos.CENTER);

		// Create stats labels, run the update method to populate the values
		winStats = new Label();
		winStats.setAlignment(Pos.CENTER);
		lossStats = new Label();
		lossStats.setAlignment(Pos.CENTER);
		updateStats();

		// Create the text field for bet entry
		TextField entry = new TextField();
		entry.setBorder(new Border(
				new BorderStroke(Color.BLUE, BorderStrokeStyle.SOLID, new CornerRadii(3.0), BorderStroke.THIN)));
		entry.setBackground(white);
		entry.setMaxWidth(40);
		entry.setAlignment(Pos.BASELINE_RIGHT);

		/*
		 * Create the functionality for the buttons
		 */

		// Roll Dice button functionality
		rollDice.setOnAction(new EventHandler<ActionEvent>() {
			// All of the work of dealing with the dice roll
			public void handle(ActionEvent ae) {

				/*
				 * Validate the contents of the text field using a helper method
				 * 
				 * If it's valid, do the random number generator and check the
				 * guess, update stats
				 *
				 * If not valid, clear the results box and change the background
				 * to red
				 * 
				 */
				if (isValid(entry.getText())) {
					// Roll the dice; add 1 since nextInt starts at 0
					int die1 = 1 + rng.nextInt(6);
					int die2 = 1 + rng.nextInt(6);
					int winner = die1 + die2;
					int playerGuess = Integer.parseInt(entry.getText());

					// Decide if the player won; true if their guess = winner
					boolean won = (playerGuess == winner);

					// Update results display and statistics
					if (won) {
						results.setText(
								String.format("You win. You bet on %d, and the dice rolled %d.", playerGuess, winner));
						wins++;
					}
					else {
						results.setText(String.format("The house wins. You bet on %d, but the dice rolled %d.",
								playerGuess, winner));
						losses++;
					}

					// Update the dice images, which are renamed as '1.png'
					// through '6.png'
					viewL.setImage(new Image(String.format("%d.png", die1)));
					viewR.setImage(new Image(String.format("%d.png", die2)));

					// Update the win and loss labels
					updateStats();
				}
				else {
					// Display error message, turn background red for invalid
					results.setText("Invalid bet");
					entry.setBackground(red);
				}
			}
		});

		// Makes the rollDice button also work if Enter is pressed, avoiding
		// having to click back into the text field to make the Enter
		// functionality reappear
		rollDice.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.ENTER) {
					rollDice.fire();
				}
			}
		});

		// Exit button functionality
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				System.exit(0);
			}
		});

		// Reset Stats button functionality
		resetStats.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				wins = 0;
				losses = 0;
				updateStats();
				entry.clear();
				results.setText("");
			}
		});

		// Handle key events in text entry box
		entry.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				// Clear results field, regardless of what's typed
				results.setText("");

				/*
				 * Check the validity of the typed key.
				 * 
				 * If it's Enter, the background is white and the rollDice
				 * button is clicked
				 * 
				 * If it's a digit, backspace, or delete, the background is
				 * white
				 * 
				 * Anything else is invalid, so the background is red
				 */
				KeyCode typed = ke.getCode();
				if (typed == KeyCode.ENTER) {
					entry.setBackground(white);
					rollDice.fire();
				}
				else if (typed.isDigitKey() || typed == KeyCode.BACK_SPACE || typed == KeyCode.DELETE) {
					entry.setBackground(white);
				}
				else {
					entry.setBackground(red);
				}

			}
		});

		/*
		 * Add all the elements to the various boxes, and show the scene
		 */
		diceRow.getChildren().addAll(viewL, viewR);
		buttonRow.getChildren().addAll(betLabel, entry, rollDice, resetStats, exit);
		statsRow.getChildren().addAll(winStats, lossStats);
		rootNode.getChildren().addAll(diceRow, buttonRow, results, statsRow);
		diceStage.show();
	}

	// Private helper method to see if a typed string can be converted to
	// a valid int from 2 to 12
	private boolean isValid(String attempt) {
		boolean result = false;
		try {
			int num = Integer.parseInt(attempt);
			if (num >= 2 && num <= 12) {
				result = true;
			}
		}
		catch (NumberFormatException e) {
		}
		return result;
	}

	// Private helper method to update the stats labels
	private void updateStats() {
		int total = wins + losses;
		double winPercentage;
		double lossPercentage;
		// Handle a new set of stats, when percentages would divide by zero
		if (total == 0) {
			winPercentage = 0;
			lossPercentage = 0;
		}
		// Otherwise calculate simple percentages of wins and losses
		else {
			winPercentage = 100 * (double) wins / total;
			lossPercentage = 100 * (double) losses / total;
		}
		// Display current wins, losses, and percentages
		winStats.setText(String.format("Wins: %d\n%.1f%%", wins, winPercentage));
		lossStats.setText(String.format("Losses: %d\n%.1f%%", losses, lossPercentage));
	}

}
