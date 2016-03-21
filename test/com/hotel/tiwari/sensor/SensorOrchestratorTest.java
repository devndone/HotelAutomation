package com.hotel.tiwari.sensor;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

import com.hotel.tiwari.data.Hotel;
import com.hotel.tiwari.factory.HotelFactory;

public class SensorOrchestratorTest {

	@Test
	public void testMovementDetectorWithAnswerEqualToY() {

		Scanner scanner = new Scanner(System.in);
		
		int mainCorridorsPerFloor = 1, subCorridorsPerFloor = 1, numberOfFloors = 1;
		
		HotelFactory factory = new HotelFactory();
		
		Hotel hotel = factory.buildHotel("TiwariInternational", numberOfFloors,
				mainCorridorsPerFloor, subCorridorsPerFloor);
		hotel.printHotelStatus();

		SensorOrchestrator sensor = new SensorOrchestrator(hotel, scanner);
		boolean movementDetected = sensor.movementDetector(scanner, 60L, hotel.getFloors().get("Floor 1")
				, hotel.getFloors().get("Floor 1").getSubCorridors().get("Floor 1Sub corridor 1"));
		
		//hotel.printHotelStatus();
		
		assertTrue(movementDetected);
		
	}
	
	@Test
	public void testMovementDetectorWithAnswerNotEqualToY() {

		Scanner scanner = new Scanner(System.in);
		
		int mainCorridorsPerFloor = 1, subCorridorsPerFloor = 1, numberOfFloors = 1;
		
		HotelFactory factory = new HotelFactory();
		
		Hotel hotel = factory.buildHotel("TiwariInternational", numberOfFloors,
				mainCorridorsPerFloor, subCorridorsPerFloor);
		hotel.printHotelStatus();

		SensorOrchestrator sensor = new SensorOrchestrator(hotel, scanner);
		boolean movementDetected = sensor.movementDetector(scanner, 60L, hotel.getFloors().get("Floor 1")
				, hotel.getFloors().get("Floor 1").getSubCorridors().get("Floor 1Sub corridor 1"));
		
		//hotel.printHotelStatus();
		
		assertFalse(movementDetected);
		
	}

}
