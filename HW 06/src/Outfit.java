/**
 * Abstract class that contains the behavior for each type of Outfit
 * 
 * @File: Outfit.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 1:43:56 PM
 */

public abstract class Outfit {
	private String type;
	
	//Clothing items
	private String hat;
	private String shoes;
	private String top;
	
	//Behaviors that are or are not possible
	private boolean canFight;
	private boolean canFly;
	private boolean canDance;

	public Outfit(String type, String hat, String shoes, String top, boolean canFight, boolean canFly, boolean canDance) {
		this.type = type;
		this.hat = hat;
		this.shoes = shoes;
		this.top = top;
		this.canFight = canFight;
		this.canFly = canFly;
		this.canDance = canDance;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * @return the clothing items needed for the outfit
	 */
	public String needToBuy() {
		return String.format("A %s hat, a pair of %s, and a %s", hat, shoes, top);
	}
	
	/**
	 * Returns a formatted version of which activities the outfit allows
	 * @author Alex Swindle
	 * @email aswindle@email.arizona.edu
	 */
	public String getActivities() {
		String activities = "";
		if(canFight) {
			activities += "Go fight!\n";
		}
		if(canFly) {
			activities += "Go fly!\n";
		}
		if(canDance) {
			activities += "Go dance!\n";
		}
		activities += "Have fun!\n";
		return activities;
	}

}
