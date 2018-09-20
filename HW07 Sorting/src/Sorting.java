/**
 * Edited by Alex Swindle aswindle@email.arizona.edu
 */

// O.Wolf
// example of a Template Pattern
// 2/20/2018

// this abstract class is going to implement a generic bubble sort

public abstract class Sorting {

	public final void bubbleSort(int[] nArray) {
		int nRounds, nElt;

		nRounds = 0;

		// Sort is done if no exchanges occurred on the previous pass
		// However, we have to loop through the array at least once, so
		// initialize the controlling boolean to true
		boolean bHasExchanged = true;
		while (bHasExchanged) {
			// If bHasExchanged is still false at the end of this run, it's
			// sorted
			bHasExchanged = false;
			// subtract an extra 1 so there's no out of bounds when rounds is 0
			for (nElt = 0; nElt < (nArray.length - nRounds - 1); nElt++) {
				if (boolNeedExchange(nArray, nArray[nElt], nArray[nElt + 1])) {
					bHasExchanged = true;
					swap(nArray, nElt, (nElt + 1));
				}
			}
			nRounds++;
		}
	}

	// Methods should be camelCase
	private final void swap(int[] nArray, int firstIndex, int secondIndex) {
		int nTemp = nArray[firstIndex];
		nArray[firstIndex] = nArray[secondIndex];
		nArray[secondIndex] = nTemp;
	}

	// Template methods
	protected abstract boolean boolNeedExchange(int[] nArray, int firstElement, int secondElement);

}
