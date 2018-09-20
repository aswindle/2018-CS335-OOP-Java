/**
 * Program: Car.java
 * 
 * Purpose: models a Car that inherits functionality from Vehicle
 * 
 * @author Alex Swindle, aswindle@email.arizona.edu
 */

public class Car extends Vehicle {
	private int numberOfDoors;
	private String bodyStyle;

	/**
	 * Empty Constructor
	 */
	public Car() {
		super();
		this.numberOfDoors = 4;
		this.bodyStyle = "unknown";
	}

	/**
	 * Constructor
	 * 
	 * @param manufacturer
	 * @param model
	 * @param engineSize
	 * @param numberOfDoors
	 * @param bodyStyle
	 */
	public Car(String manufacturer, String model, String engineSize, int numberOfDoors, String bodyStyle) {
		super(manufacturer, model, engineSize);
		setNumberOfDoors(numberOfDoors);
		setBodyStyle(bodyStyle);
	}

	/**
	 * @return the formatted fields
	 */
	public String toString() {
		String vehicleString = super.toString();
		String carString = String.format("; Number of Doors: %d; Body Style: %s", numberOfDoors, bodyStyle);
		return vehicleString + carString;
	}

	// Getters and Setters
	/**
	 * @return the numberOfDoors
	 */
	public int getNumberOfDoors() {
		return numberOfDoors;
	}

	/**
	 * @param numberOfDoors
	 *            the numberOfDoors to set
	 */
	public void setNumberOfDoors(int numberOfDoors) {
		this.numberOfDoors = numberOfDoors;
	}

	/**
	 * @return the bodyStyle
	 */
	public String getBodyStyle() {
		return bodyStyle;
	}

	/**
	 * @param bodyStyle
	 *            the bodyStyle to set
	 */
	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}
}
