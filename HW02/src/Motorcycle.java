/**
 * Program: Motorcycle.java
 * 
 * Purpose: models a Motorcycle that inherits functionality from Vehicle
 * 
 * @author Alex Swindle, aswindle@email.arizona.edu
 */

public class Motorcycle extends Vehicle {
	private String handleBars;
	private String fairing;

	/**
	 * Empty constructor
	 */
	public Motorcycle() {
		super();
		setHandleBars("unknown");
		setFairing("unknown");
	}

	/**
	 * Constructor
	 * 
	 * @param manufacturer
	 * @param model
	 * @param engineSize
	 * @param handleBars
	 * @param fairing
	 */
	public Motorcycle(String manufacturer, String model, String engineSize, String handleBars, String fairing) {
		super(manufacturer, model, engineSize);
		setHandleBars(handleBars);
		setFairing(fairing);
	}

	/**
	 * @return the formatted fields
	 */
	public String toString() {
		String vehicleString = super.toString();
		String cycleString = String.format("; Handlebars: %s; Fairing: %s", handleBars, fairing);
		return vehicleString + cycleString;
	}

	// Getters and Setters
	/**
	 * @return the handleBars
	 */
	public String getHandleBars() {
		return handleBars;
	}

	/**
	 * @param handleBars
	 *            the handleBars to set
	 */
	public void setHandleBars(String handleBars) {
		this.handleBars = handleBars;
	}

	/**
	 * @return the fairing
	 */
	public String getFairing() {
		return fairing;
	}

	/**
	 * @param fairing
	 *            the fairing to set
	 */
	public void setFairing(String fairing) {
		this.fairing = fairing;
	}

}
