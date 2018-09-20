
/**
 * Edited by Alex Swindle
 * aswindle@email.arizona.edu 
 */
// O.Wolf
// example of a Template Pattern
// 2/20/2018

import java.util.Random;

// Classes should be capitalized; renamed file to match
public class Main {

	// methods should be camelCased
	public static void fillArray(int[] nArray) {
		// create instance of Random class
		Random rand = new Random();
		for (int i = 0; i < nArray.length; i++) {
			nArray[i] = rand.nextInt(100);
		}

	}

	// methods should be camelCased
	public static void printArray(int[] nArray) {
		for (int i = 0; i < nArray.length; i++) {
			System.out.print(nArray[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] nMyArray;
		nMyArray = new int[10];
		fillArray(nMyArray);
		Sorting myDescendingSort = new DescendingSort();
		Sorting myAscendingSort = new AscendingSort();
		System.out.println("original array");
		printArray(nMyArray);

		System.out.println("descending order");
		myDescendingSort.bubbleSort(nMyArray);

		printArray(nMyArray);

		System.out.println("Ascending order");
		myAscendingSort.bubbleSort(nMyArray);
		printArray(nMyArray);

	}

}
