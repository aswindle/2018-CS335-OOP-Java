/**
 * @File: ClassCard.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 28, 2018
 * @Last modified: 9:40:26 AM
 */

public class ClassCard extends Card{
	private String cardClass;
	
	public ClassCard(String type, String cardClass) {
		super(type);
		this.cardClass = cardClass;
	}
	
	/**
	 * Constructor with only one parameter
	 * 
	 * @param cardClass
	 */
	public ClassCard(String cardClass) {
		super("Class");
		this.cardClass = cardClass;
	}

	/**
	 * @return the cardClass
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public String getCardClass() {
		return cardClass;
	}

	/**
	 * @param cardClass the cardClass to set
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public void setClass(String cardClass) {
		this.cardClass = cardClass;
	}
	
	public String toString() {
		return "Class Card\n" + this.cardClass;
	}
	
	
}
