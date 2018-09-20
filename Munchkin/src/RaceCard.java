/**
 * @file: RaceCard.java
 *
 * Purpose:
 *
 * @package: 
 * 
 * @author: Daniel Koblas
 * @email : koblas@email.arizona.edu
 * 
 * @date: Feb 21, 2018 12:06:44 PM
 */

/**
 *
 *
 * @author: Daniel Koblas
 * @email : koblas@email.arizona.edu
 *
 */
public class RaceCard extends Card {

	private String race;
	
	/**
	 * Constructor 
	 *
	 * @param type
	 */
	public RaceCard(String type, String race) {
		super(type);
		this.setRace(race);
	}

	public RaceCard(String race) {
		this("Race", race);
	}
	
	public String getRace() {
		return race;
	}
	
	public void setRace(String race) {
		this.race = race;
	}
	
	public String toString() {
		return String.format("Race Card\n%s", race);
	}
	
}
