/**
 * @File: Curse.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 10:14:31 AM
 */

public class CurseCard extends Card {
	private String CURSE1 = "Lose 1 Card";
	private String CURSE2 = "Lose 2 Cards";
	private String CURSE3 = "Lose Race";
	private String CURSE45 = "Get New Race";
	private String CURSE67 = "Lose Class";
	private String CURSE8910 = "Lose a Level";
	
	private int curseNumber;

	public CurseCard(String type, int number) {
		super(type);
		this.curseNumber = number;
	}

	/**
	 * Getter for curse number
	 * 
	 * @return curseNumber
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public int getCurseNumber() {
		return this.curseNumber;
	}
	
	/**
	 * Prints out all of the data on a Curse card
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	@Override
	public String toString() {
		String curseText;
		switch(curseNumber) {
		case 1:
			curseText = CURSE1;
			break;
		case 2:
			curseText = CURSE2;
			break;
		case 3:
			curseText = CURSE3;
			break;
		case 4:
		case 5:
			curseText = CURSE45;
			break;
		case 6:
		case 7:
			curseText = CURSE67;
			break;
		default:
			curseText = CURSE8910;
			break;
		}
		return super.toString() + "\nCurse # " + curseNumber + '\n' + curseText;
	}
}
