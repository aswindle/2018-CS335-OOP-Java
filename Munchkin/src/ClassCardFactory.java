/**
 * @File: CardClassFactory.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 28, 2018
 * @Last modified: 9:43:25 AM
 */

public class ClassCardFactory implements CardFactory {

	/**
	 * Creates a new ClassCard; must be passed a class type
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public Card createCard(String cardClass) {
		return new ClassCard("Class", cardClass);
	}
	
	/* (non-Javadoc)
	 * @see CardFactory#createCard()
	 */
	@Override
	public Card createCard() {
		// must be passed a card type to get a card here.
		throw new IllegalArgumentException("Class card factory requires class to be specified (i.e. createCard(\"Warrior\")).");
	}

}
