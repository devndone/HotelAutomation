package com.hotel.tiwari.common;

import com.hotel.tiwari.data.Floor;

public class PowerConsumptionLimit {
	
	private int minForFloor;
	private int maxForFloor;
	public static final int MIN_MAIN_CORRIDOR = 0;
	public static final int MAX_MAIN_CORRIDOR = 15;
	public static final int MIN_SUB_CORRIDOR = 0;
	public static final int MAX_SUB_CORRIDOR = 10;
	public static final int MIN_AC = 0;
	public static final int MAX_AC = 10;
	public static final int MIN_LIGHT = 0;
	public static final int MAX_LIGHT = 5;
	
	public int getMinForFloor() {
		return minForFloor;
	}
	public void setMinForFloor(int minForFloor) {
		this.minForFloor = minForFloor;
	}
	
	public int getMaxForFloor() {
		return maxForFloor;
	}
	public void setMaxForFloor(int maxForFloor) {
		this.maxForFloor = maxForFloor;
	}
	
	public static int calculateMaxForFloor(int numberOfMainCorridor, int unitPerMainCorridor, 
			int numberOfSubCorridor, int unitPerSubCorridor) {
		return ((numberOfMainCorridor * unitPerMainCorridor) + (numberOfSubCorridor * unitPerSubCorridor));
	}
	
	public static int calculateFloorPowerConsumptionDisparity(Floor floor) {
		int fpocons = floor.getPowerConsumption();
		//System.out.println("if..previous..fpocons -> " + fpocons);
		int maxfpocons = PowerConsumptionLimit.calculateMaxForFloor(floor.getMainCorridors().size(), PowerConsumptionLimit.MAX_MAIN_CORRIDOR
				, floor.getSubCorridors().size(), PowerConsumptionLimit.MAX_SUB_CORRIDOR);
		//System.out.println("if..maxfpocons -> " + maxfpocons);
		return (maxfpocons - fpocons);
	}
	
}
