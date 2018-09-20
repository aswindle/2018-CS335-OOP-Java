
/**
 * @File: OutfitClient.java
 * 
 * @Author Alex Swindle
 * @Email aswindle@email.arizona.edu
 * 
 * @Date: Feb 19, 2018
 * @Last modified: 2:06:49 PM
 */

import java.util.ArrayList;
import java.util.Scanner;

public class OutfitClient {
	private static OutfitFactory factory;
	private static String outfitOptions = "Which outfit would you like?\n1. Pirate\n2. Fairy\n3. Magician\n4. Noble\n";

	public static void main(String[] args) {
		factory = new OutfitFactory();
		Scanner input = new Scanner(System.in);

		// Have 10 kids get their outfits
		int kidCount = 0;
		ArrayList<Kid> kids = new ArrayList<Kid>();

		while (kidCount < 10) {
			kidCount++;
			System.out.print("Kid " + kidCount + ", please enter your name: ");
			String kidName = input.next();
			Outfit kidOutfit;

			boolean validOutfit = false;
			int outfitType = 0;
			while (!validOutfit) {
				System.out.print(outfitOptions);
				String attempt = input.next();
				if (isValid(attempt, 1, 4)) {
					outfitType = Integer.parseInt(attempt);
					validOutfit = true;
				}
			}
			kidOutfit = factory.createOutfit(outfitType);
			Kid newKid = new Kid(kidName, kidOutfit);
			kids.add(newKid);
			
			//Print out the current kid's shopping list and activities they can do
			System.out.printf("OK, %s, you need to go buy:\n%s\nNow you can:\n%s", newKid.getName(),
					newKid.getOutfit().needToBuy(), newKid.getOutfit().getActivities());
		}
		
		//Print out the final list of kids and their costumes
		System.out.println("List of kids:");
		for(Kid curKid: kids) {
			System.out.printf("%s: %s\n", curKid.getName(), curKid.getOutfit().getType());
		}
	}

	/**
	 * Checks to see if a string can be converted to int between a lower and
	 * upper bound
	 * 
	 * @param attempt:
	 *            String to try to parse as an integer
	 * @param lowerBound:
	 *            smallest acceptable int
	 * @param upperBound:
	 *            largest acceptable int
	 * @return true if valid, false if not
	 */
	private static boolean isValid(String attempt, int lowerBound, int upperBound) {
		boolean valid = false;
		// Try to parse attempt as an int; if possible, check if it's between
		// lower and upper bounds
		try {
			int action = Integer.parseInt(attempt);
			if (action >= lowerBound && action <= upperBound) {
				valid = true;
			}
			else {
				System.out.println("Error: choice must be between  " + lowerBound + " and " + upperBound);
			}
		}
		catch (NumberFormatException e) {
			System.out.println("Error: enter an integer between " + lowerBound + " and " + upperBound);
		}
		return valid;
	}

}
