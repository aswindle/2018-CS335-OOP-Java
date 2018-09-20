/**
 * @File: OutfitFactory.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 1:48:33 PM
 */

public class OutfitFactory {
	public Outfit createOutfit(int type) {
		Outfit retval;
		switch (type) {
		case 1:
			retval = new Pirate();
			break;
		case 2:
			retval = new Fairy();
			break;
		case 3:
			retval = new Magician();
			break;
		case 4:
			retval = new Noble();
			break;
		default:
			retval = new Pirate();
			break;
		}
		return retval;
	}
}
