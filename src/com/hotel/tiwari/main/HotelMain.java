/**
 * 
 */
package com.hotel.tiwari.main;

import java.util.Scanner;

import com.hotel.tiwari.data.Hotel;
import com.hotel.tiwari.factory.HotelFactory;
import com.hotel.tiwari.sensor.SensorOrchestrator;

public class HotelMain {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		HotelFactory factory = new HotelFactory();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Number of floors: ");
		int numberOfFloors = scanner.nextInt();

		System.out.println("Main corridors per floor: ");
		int mainCorridorsPerFloor = scanner.nextInt();

		System.out.println("Sub corridors per floor: ");
		int subCorridorsPerFloor = scanner.nextInt();
		
		//PowerConsumptionLimit powerConsumptionLimit = new PowerConsumptionLimit();
		//powerConsumptionLimit.setMinForFloor(0);
		//powerConsumptionLimit.setMaxForFloor(PowerConsumptionLimit.calculateMaxForFloor(mainCorridorsPerFloor, PowerConsumptionLimit.MAX_MAIN_CORRIDOR, 
		//																subCorridorsPerFloor, PowerConsumptionLimit.MAX_SUB_CORRIDOR));

		Hotel hotel = factory.buildHotel("TiwariInternational", numberOfFloors,
				mainCorridorsPerFloor, subCorridorsPerFloor);
		hotel.printHotelStatus();

		SensorOrchestrator sensor = new SensorOrchestrator(hotel, scanner);
		Thread sensorProcess = new Thread(sensor);
		sensorProcess.start();
		
		// System.out.println("Movement detected in coridor Floor:1Main:1");
		//sensor.passOnsensor("Floor 1", "Floor 1Sub corridor 1");

		// System.out.println("Movement detected in coridor Floor:2Main:1");
		//sensor.passOnsensor("Floor 2", "Floor 2Sub corridor 1");

		// System.out.println("Power save No Movement detected in coridor Floor:2Main:1");
		//sensor.passOffsensor("Floor 1", "Floor 1Sub corridor 1");

		sensorProcess.join();
		scanner.close();
		
	}

}
