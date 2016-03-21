package com.hotel.tiwari.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.hotel.tiwari.contract.instruments.Instrument;

public class Floor {

	private String floorName;
	private Map<String, Corridor> mainCorridors;
	private Map<String, Corridor> subCorridors;
	private volatile int powerConsumption;

	public Floor(String floorName, Map<String, Corridor> mainCorridors,
			Map<String, Corridor> subCorridors) {
		super();
		this.floorName = floorName;
		this.mainCorridors = mainCorridors;
		this.subCorridors = subCorridors;
		this.powerConsumption = this.calulatePowerConsumption();
	}

	public String getFloorName() {
		return floorName;
	}

	public Map<String, Corridor> getMainCorridors() {
		return Collections.unmodifiableMap(mainCorridors);
	}

	public Map<String, Corridor> getSubCorridors() {
		return Collections.unmodifiableMap(subCorridors);
	}

	public int getPowerConsumption() {
		return (this.powerConsumption = this.calulatePowerConsumption());
	}
	
	private int calulatePowerConsumption() {
		int pocons = 0;
		List<Instrument> ins = new ArrayList<>();
		if(this.mainCorridors != null) {
			for(String k : this.mainCorridors.keySet()) {
				ins.addAll(this.mainCorridors.get(k).getAcList());
				ins.addAll(this.mainCorridors.get(k).getLightList());
			}
		}
		if(this.subCorridors != null) {
			for(String k : this.subCorridors.keySet()) {
				ins.addAll(this.subCorridors.get(k).getAcList());
				ins.addAll(this.subCorridors.get(k).getLightList());
			}
		}
		for(Instrument i : ins) {
			pocons += i.getPowerConsumption();
		}
		return pocons;
	}

	public void registerMainCorridor(String CorridorId, Corridor Corridor) {
		this.mainCorridors.put(CorridorId, Corridor);
	}
	
	public void registerSubCorridor(String CorridorId, Corridor Corridor) {
		this.subCorridors.put(CorridorId, Corridor);
	}
	
}
