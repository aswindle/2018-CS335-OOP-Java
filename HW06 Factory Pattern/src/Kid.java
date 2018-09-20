/**
 * Container for a kid's name and outfit
 * 
 * @File: Kid.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 2:22:15 PM
 */

public class Kid {
	private String name;
	private Outfit outfit;

	public Kid(String name, Outfit outfit) {
		this.name = name;
		this.outfit = outfit;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the outfit
	 */
	public Outfit getOutfit() {
		return outfit;
	}
	
	
}
