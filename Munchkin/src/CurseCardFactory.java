/**
 * @File: CurseCardFactory.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 10:16:37 AM
 */
import java.util.Random;

public class CurseCardFactory implements CardFactory{
	Random rng = new Random();
	
	public Card createCard() {
		int curseNumber = 1+ rng.nextInt(10);
		return new CurseCard("Curse", curseNumber);
	}
}
