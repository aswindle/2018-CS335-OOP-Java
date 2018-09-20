/**
 * @File: CardButton.java
 * 
 * Creates a ToggleButton that's tied to a specific Card that can then be accessed
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Mar 2, 2018
 * @Last modified: 9:42:55 PM
 */
import javafx.scene.control.ToggleButton;

public class CardButton extends ToggleButton{
	private Card card;
	
	public CardButton(Card card) {
		super();
		this.card = card;
		this.setText(card.toString());
	}
	
	public Card getCard() {
		return this.card;
	}
}
