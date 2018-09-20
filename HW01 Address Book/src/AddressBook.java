/*	Program: AddressBook.java
 *
 *	Purpose: Models a simple address book consisting of names and phone numbers; allows lookup by name. 
 *		If the name is found, prints out the name followed by the associated phone number;
 *		otherwise, prints "'<name>' not found"
 * 
 *	Author: Alex Swindle, aswindle@email.arizona.edu
 */
import java.util.Scanner;

public class AddressBook
{
	// Create arrays of names and numbers
	final static String[] names = {"Wayne, Bruce", "Kent, Clark", "Prince, Diana", "Allen, Barry", "J'onnz, J'onn", 
			"Jordan, Hal", "Stewart, John", "Gardner, Guy", "Rayner, Kyle", "Wayne, Damian"};
	final static String[] phoneNumbers = {"867-5309", "123-4567", "555-1234", "800-2345", "909-0909",
			"345-6789", "987-6543", "101-0101", "246-8101", "135-7911"};
	
	public static void main(String[] args) {		
		// Create a Scanner to take user input from keyboard
		Scanner kb = new Scanner(System.in);
		
		// Read text from the user until they type "exit", look up entered value
		System.out.println("Enter a name to look up (Last, First) or 'Exit':");
		String input = kb.nextLine();
		while(input.toLowerCase().equals("exit") == false) {
			lookup(input);
			
			// Get the next value
			System.out.println("Enter a name to look up (Last, First) or 'Exit'");
			input = kb.nextLine();
		}
		kb.close();
	}
	
	// Searches sequentially through the list of names for a specific value.
	// If the name exists, prints the name followed by the associated number
	// Otherwise, prints "'<name>' not found"
	public static void lookup(String name) {
		for(int i = 0; i < names.length; i++) {
			if(name.equals(names[i])) {
				System.out.println(String.format("Name: %s Phone Number: %s", names[i], phoneNumbers[i]));
				return;
			}
		}

		// name wasn't found in the names array
		System.out.println(String.format("'%s' not found", name));
	}

}
