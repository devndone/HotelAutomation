package com.hotel.tiwari.common;

import static org.junit.Assert.*;

import java.util.Map.Entry;

import org.junit.Test;

import com.hotel.tiwari.data.Floor;
import com.hotel.tiwari.data.Hotel;
import com.hotel.tiwari.factory.HotelFactory;

public class PowerConsumptionLimitTest {

	@Test
	public void testCalculateMaxForFloor() {
		
		int numberOfMainCorridor = 2, numberOfSubCorridor = 2;
		
		int[] expecteds = new int[1];
		int[] actuals = new int[1];
		
		expecteds[0] = ((numberOfMainCorridor * PowerConsumptionLimit.MAX_MAIN_CORRIDOR) + (numberOfSubCorridor * PowerConsumptionLimit.MAX_SUB_CORRIDOR));
		actuals[0] = PowerConsumptionLimit.calculateMaxForFloor(numberOfMainCorridor, PowerConsumptionLimit.MAX_MAIN_CORRIDOR, 
												numberOfSubCorridor, PowerConsumptionLimit.MAX_SUB_CORRIDOR);
		assertArrayEquals(expecteds, actuals);
	}

	@Test
	public void testCalculateFloorPowerConsumptionDisparity() {
		
		int numberOfMainCorridor = 2, numberOfSubCorridor = 2, numberOfFloors = 2;
		
		int maxfpocons = ((numberOfMainCorridor * PowerConsumptionLimit.MAX_MAIN_CORRIDOR) + (numberOfSubCorridor * PowerConsumptionLimit.MAX_SUB_CORRIDOR));
		
		HotelFactory factory = new HotelFactory();
	
		Hotel hotel = factory.buildHotel("TiwariInternational", numberOfFloors,
				numberOfMainCorridor, numberOfSubCorridor);
		
		int[] expecteds = new int[hotel.getFloors().entrySet().size()];
		int[] actuals = new int[hotel.getFloors().entrySet().size()];
		int idx = 0;
		
		for(Entry<String, Floor> f : hotel.getFloors().entrySet()) {
			expecteds[idx] = maxfpocons - f.getValue().getPowerConsumption();
			actuals[idx] = PowerConsumptionLimit.calculateFloorPowerConsumptionDisparity(f.getValue());
			++idx;
		}
		
		assertArrayEquals(expecteds, actuals);
	}

}
