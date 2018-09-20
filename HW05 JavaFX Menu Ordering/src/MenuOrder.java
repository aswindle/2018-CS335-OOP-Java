
/**
 * Program: MenuOrder.java
 * 
 * Purpose: Simple GUI implementation of a food ordering menu
 * 
 * Icons are from https://www.flaticon.com/packs/
 * Creator: Smashicons (food and drink); Gregor Cresnar (shopping cart)
 * 
 * @author Alex Swindle
 * @email aswindle@gmail.com
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuOrder extends Application {
	private Label results;

	public static void main(String[] args) {
		launch();
	}

	public void start(Stage menuStage) {
		/*
		 * Set up the stage: title, root node, scene
		 */
		menuStage.setTitle("Menu Order Form");
		VBox rootNode = new VBox(10);
		rootNode.setAlignment(Pos.CENTER);
		Scene orderScene = new Scene(rootNode, 500, 500);
		menuStage.setScene(orderScene);

		/*
		 * Create all of the images for the buttons
		 */
		// Food
		ImageView burgerImg = new ImageView(new Image("Icons/hamburger.png"));
		burgerImg.setFitWidth(100);
		burgerImg.setPreserveRatio(true);

		ImageView sandwichImg = new ImageView(new Image("Icons/sandwich.png"));
		sandwichImg.setFitWidth(100);
		sandwichImg.setPreserveRatio(true);

		ImageView pizzaImg = new ImageView(new Image("Icons/pizza.png"));
		pizzaImg.setFitWidth(100);
		pizzaImg.setPreserveRatio(true);

		// Drinks
		ImageView coffeeImg = new ImageView(new Image("Icons/coffee.png"));
		coffeeImg.setFitWidth(100);
		coffeeImg.setPreserveRatio(true);

		ImageView teaImg = new ImageView(new Image("Icons/tea.png"));
		teaImg.setFitWidth(100);
		teaImg.setPreserveRatio(true);

		ImageView canImg = new ImageView(new Image("Icons/can.png"));
		canImg.setFitWidth(100);
		canImg.setPreserveRatio(true);

		// Shopping cart for order button
		ImageView cartImg = new ImageView(new Image("Icons/shoppingcart.png"));
		cartImg.setFitWidth(100);
		cartImg.setPreserveRatio(true);

		/*
		 * Create the results feedback field with room for 3 lines of text
		 */
		results = new Label("Please select your order.");
		results.setPrefHeight(180);

		/*
		 * Create the groups for the buttons to allow mutual exclusivity
		 */
		ToggleGroup foodGroup = new ToggleGroup();
		ToggleGroup drinkGroup = new ToggleGroup();

		/*
		 * Create all of the buttons, add to correct ToggleGroup
		 */
		// Food
		ToggleButton burgerButton = new ToggleButton("Hamburger", burgerImg);
		burgerButton.setContentDisplay(ContentDisplay.TOP);
		burgerButton.setToggleGroup(foodGroup);

		ToggleButton sandwichButton = new ToggleButton("Sandwich", sandwichImg);
		sandwichButton.setContentDisplay(ContentDisplay.TOP);
		sandwichButton.setToggleGroup(foodGroup);

		ToggleButton pizzaButton = new ToggleButton("Pizza", pizzaImg);
		pizzaButton.setContentDisplay(ContentDisplay.TOP);
		pizzaButton.setToggleGroup(foodGroup);

		// Drinks
		ToggleButton coffeeButton = new ToggleButton("Coffee", coffeeImg);
		coffeeButton.setContentDisplay(ContentDisplay.TOP);
		coffeeButton.setToggleGroup(drinkGroup);

		ToggleButton teaButton = new ToggleButton("Tea", teaImg);
		teaButton.setContentDisplay(ContentDisplay.TOP);
		teaButton.setToggleGroup(drinkGroup);

		ToggleButton canButton = new ToggleButton("Soda", canImg);
		canButton.setContentDisplay(ContentDisplay.TOP);
		canButton.setToggleGroup(drinkGroup);

		// Order Button
		Button orderButton = new Button("Order", cartImg);
		orderButton.setContentDisplay(ContentDisplay.TOP);

		/*
		 * Create the boxes to hold the various buttons, add them to the scene
		 */
		HBox foodRow = new HBox(10);
		foodRow.getChildren().addAll(burgerButton, sandwichButton, pizzaButton);
		foodRow.setAlignment(Pos.CENTER);

		HBox drinkRow = new HBox(10);
		drinkRow.getChildren().addAll(coffeeButton, teaButton, canButton);
		drinkRow.setAlignment(Pos.CENTER);

		rootNode.getChildren().addAll(foodRow, drinkRow, orderButton, results);

		/*
		 * Handle all of the buttons
		 * 
		 * Food and drink buttons will display a message stating whether the
		 * button was selected or unselected
		 * 
		 * Order will combine the results and print current order
		 */

		// Food and Drink button functionality
		burgerButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				buttonResults(burgerButton);
			}
		});

		sandwichButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				buttonResults(sandwichButton);
			}
		});

		pizzaButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				buttonResults(pizzaButton);
			}
		});

		coffeeButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				buttonResults(coffeeButton);
			}
		});

		teaButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				buttonResults(teaButton);
			}
		});

		canButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent ae) {
				buttonResults(canButton);
			}
		});

		// Handle the Order button being clicked
		orderButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent ae) {
				String food;
				String drink;

				// Figure out what food was selected
				ToggleButton clickedFood = (ToggleButton) foodGroup.getSelectedToggle();
				if (clickedFood != null) {
					food = clickedFood.getText();
				}
				else {
					food = "No main item selected.";
				}

				// Figure out what drink was selected
				ToggleButton clickedDrink = (ToggleButton) drinkGroup.getSelectedToggle();
				if (clickedDrink != null) {
					drink = clickedDrink.getText();
				}
				else {
					// Haven't clicked a button
					drink = "No drink selected";
				}

				// Combine the results, update results field
				String resultText = String.format("Current Order:\nMain item: %s\nDrink: %s", food, drink);
				results.setText(resultText);
			}
		});
		menuStage.show();
	}

	// Helper method to handle the clicking of a ToggleButton
	private void buttonResults(ToggleButton clicked) {
		if (clicked.isSelected()) {
			results.setText(String.format("%s selected", clicked.getText()));
		}
		else {
			results.setText(String.format("%s unselected", clicked.getText()));
		}
	}

}
