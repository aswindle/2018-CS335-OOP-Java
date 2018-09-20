/**
 * Program: Vehicle.java
 * 
 * Purpose: models an abstract Vehicle class that Car, Truck, and Motorcycle
 * classes will be derived from
 * 
 * @author Alex Swindle, aswindle@email.arizona.edu
 */

public abstract class Vehicle {
	private String manufacturer;
	private String model;
	private String engineSize;

	/**
	 * Empty constructor
	 */
	public Vehicle() {
		setEngineSize("unknown");
		setManufacturer("unknown");
		setModel("unknown");
	}

	/**
	 * Constructor
	 * 
	 * @param manufacturer
	 * @param model
	 * @param engineSize
	 */
	public Vehicle(String manufacturer, String model, String engineSize) {
		setManufacturer(manufacturer);
		setModel(model);
		setEngineSize(engineSize);
	}

	/**
	 * @return the formatted fields
	 */
	public String toString() {
		return String.format("Manufacturer: %s; Model: %s; Engine Size: %s", manufacturer, model, engineSize);
	}

	// Getters and Setters
	/**
	 * @return the engineSize
	 */
	public String getEngineSize() {
		return engineSize;
	}

	/**
	 * @param engineSize
	 *            the engineSize to set
	 */
	public void setEngineSize(String engineSize) {
		this.engineSize = engineSize;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
}
