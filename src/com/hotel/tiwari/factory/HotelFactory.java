package com.hotel.tiwari.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import com.hotel.tiwari.contract.instruments.CoridorType;
import com.hotel.tiwari.contract.instruments.Instrument;
import com.hotel.tiwari.contract.instruments.Status;
import com.hotel.tiwari.data.AC;
import com.hotel.tiwari.data.Corridor;
import com.hotel.tiwari.data.Floor;
import com.hotel.tiwari.data.Hotel;
import com.hotel.tiwari.data.Light;

public class HotelFactory {

	public Hotel buildHotel(String name, int noOfFloor, int mainPerFloor,
			int subPerFloor) {
		
		Map<String, Floor> floors = new ConcurrentSkipListMap<String, Floor>();
		Hotel hotel = new Hotel(name, floors);

		// Populate Foor
		for (int i = 1; i <= noOfFloor; i++) {

			Map<String, Corridor> mainCoridors = new ConcurrentSkipListMap<>();
			Map<String, Corridor> subCoridors = new ConcurrentSkipListMap<>();

			;
			String floorName = "Floor " + i;

			Floor floor = new Floor(floorName, mainCoridors, subCoridors);
			floors.put(floorName, floor);

			// Populate main coridor
			for (int m = 1; m <= mainPerFloor; m++) {
				this.populateCorridor(m, hotel, floorName, CoridorType.MAIN,
						mainCoridors);
			}
			// Populate sub coridor
			for (int s = 1; s <= subPerFloor; s++) {
				this.populateCorridor(s, hotel, floorName, CoridorType.SUB,
						subCoridors);
			}

		}
		return hotel;

	}

	private void populateCorridor(int c, Hotel hotel, String floorName,
			CoridorType coridorType, Map<String, Corridor> corridors) {

		String coridorName;
		if (CoridorType.MAIN == coridorType) {
			coridorName = "Main corridor " + c;
		} else {
			coridorName = "Sub corridor " + c;
		}

		String coridorId = floorName + coridorName;

		List<Instrument> acList = new ArrayList<Instrument>(1);
		List<Instrument> lightList = new ArrayList<Instrument>(1);

		Instrument ac = new AC("AC " + c, Status.ON);
		Instrument light = null;

		if (CoridorType.MAIN == coridorType) {
			light = new Light("Light " + c, Status.ON);
		} else {
			light = new Light("Light " + c, Status.OFF);
		}

		acList.add(ac);
		lightList.add(light);

		Corridor corridor = new Corridor(coridorName, coridorId, coridorType,
				acList, lightList);
		if (CoridorType.MAIN == coridorType) {
			hotel.getFloors().get(floorName)
					.registerMainCorridor(coridorId, corridor);
		} else {
			hotel.getFloors().get(floorName)
					.registerSubCorridor(coridorId, corridor);
		}

		corridors.put(coridorId, corridor);
	}

}
