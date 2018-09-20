/**
 * Program: VehicleClient.java
 * 
 * Purpose: tests the functionality of Car, Truck, and Motorcycle classes
 * 
 * @author Alex Swindle, aswindle@email.arizona.edu
 */

public class VehicleClient {

	public static void main(String[] args) {
		// Test a car using the default constructor
		Car car1 = new Car();
		System.out.println(car1);
		// Test all the setters, print results
		car1.setManufacturer("Toyota");
		car1.setModel("Corolla");
		car1.setEngineSize("V6");
		car1.setBodyStyle("Compact");
		car1.setNumberOfDoors(4);
		System.out.println(car1);

		// Test a truck using the full constructor
		Truck truck1 = new Truck("Ford", "F150", "V8", 2, 12.0);
		System.out.println(truck1);
		// Test all the getters, print results
		String truckModel = truck1.getModel();
		String truckMfg = truck1.getManufacturer();
		String truckEngine = truck1.getEngineSize();
		int truckDoors = truck1.getNumberOfDoors();
		double truckBed = truck1.getBedLength();
		System.out.println(String.format("Values returned from getters: %s %s %s %d %.1f", truckMfg, truckModel,
				truckEngine, truckDoors, truckBed));

		// Test a motorcycle
		Motorcycle cycle1 = new Motorcycle();
		System.out.println(cycle1);

	}

}
