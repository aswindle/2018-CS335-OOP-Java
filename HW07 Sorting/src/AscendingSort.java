/**
 * Edited by Alex Swindle aswindle@email.arizona.edu
 */

// O.Wolf
// example of a Template Pattern
// 2/20/2018

// provides an ascending sort

public class AscendingSort extends Sorting {

	@Override
	// Fixed 'Ecchange' spelling error
	protected boolean boolNeedExchange(int[] nArray, int firstElement, int secondElement) {
		boolean needExchange = false;
		if (firstElement > secondElement) {
			needExchange = true;
		}
		return needExchange;
	}

}
