/**
 * Program: Truck.java
 * 
 * Purpose: models a Truck that inherits functionality from Vehicle
 * 
 * @author Alex Swindle, aswindle@email.arizona.edu
 */

public class Truck extends Vehicle {
	private int numberOfDoors;
	private double bedLength;

	/**
	 * Empty constructor
	 */
	public Truck() {
		super();
		this.numberOfDoors = 4;
		this.bedLength = 0;
	}

	/**
	 * Constructor
	 * 
	 * @param manufacturer
	 * @param model
	 * @param engineSize
	 * @param numberOfDoors
	 * @param bedLength
	 */
	public Truck(String manufacturer, String model, String engineSize, int numberOfDoors, double bedLength) {
		super(manufacturer, model, engineSize);
		setNumberOfDoors(numberOfDoors);
		setBedLength(bedLength);
	}

	/**
	 * @return the formatted fields
	 */
	public String toString() {
		String vehicleString = super.toString();
		String truckString = String.format("; Number of Doors: %d; Bed Length: %.1f", numberOfDoors, bedLength);
		return vehicleString + truckString;
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
	 * @return the bedLength
	 */
	public double getBedLength() {
		return bedLength;
	}

	/**
	 * @param bedLength
	 *            the bedLength to set
	 */
	public void setBedLength(double bedLength) {
		this.bedLength = bedLength;
	}
}
