/**
 * Monster card type
 * 
 * @File: Monster.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 7:36:45 AM
 */

public class MonsterCard extends Card{
	private int level;
	private boolean undead;
	private int vulnerability;
	private int goodStuff;
	private int badStuff;
	
	public MonsterCard(String type, int level, boolean undead, int vulnerability, int goodStuff, int badStuff) {
		super(type);
		this.setUndead(undead);
		this.level = level;
		this.vulnerability = vulnerability;
		this.goodStuff = goodStuff;
		this.badStuff = badStuff;
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
	 * @return the vulnerability
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public int getVulnerability() {
		return vulnerability;
	}

	/**
	 * @return the goodStuff
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public int getGoodStuff() {
		return goodStuff;
	}

	/**
	 * @return the badStuff
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public int getBadStuff() {
		return badStuff;
	}

	/**
	 * @return the undead
	 */
	public boolean isUndead() {
		return undead;
	}

	/**
	 * @param undead the undead to set
	 */
	public void setUndead(boolean undead) {
		this.undead = undead;
	}
	
	/**
	 * Prints out all of the data on a Monster card
	 * 
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	@Override
	public String toString() {
		return super.toString() + String.format("\nLevel: %d\nUndead: %b\nVulnerability: %d\nGoodStuff: %d\nBadStuff: %d", level, undead, vulnerability, goodStuff, badStuff);
	}
	
	
}
