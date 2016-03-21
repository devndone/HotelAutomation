package com.hotel.tiwari.data;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

import com.hotel.tiwari.contract.instruments.Instrument;

public class Hotel {

	private String name;
	private Map<String, Floor> floors;

	public Hotel(String name) {
		super();
		this.name = name;
		this.floors = new ConcurrentSkipListMap<String, Floor>();
	}

	public Hotel(String name, Map<String, Floor> floors) {
		super();
		this.name = name;
		this.floors = floors;
	}
	
	public Map<String, Floor> getFloors() {
		return Collections.unmodifiableMap(this.floors);
	}

	@Override
	public String toString() {
		return "Hotel [name=" + name + ", floors=" + floors + "]";
	}

	public synchronized void printHotelStatus() {
		Set<String> flrs = this.floors.keySet(); 
		for (String floor : flrs) {
			System.out.println(this.floors.get(floor).getFloorName());
			Set<String> mainc = this.floors.get(floor).getMainCorridors().keySet();
			for(String mc : mainc) {
				System.out.print("\t");
				this.printCorridor(this.floors.get(floor).getMainCorridors().get(mc));
			}
			this.printCorridor(this.floors.get(floor).getSubCorridors());
			System.out.println("");
		}
	}

	private void printCorridor(Corridor coridor) {
			System.out.println(coridor.getCoridorName());
			System.out.print("\t");
			this.printInstrument(coridor.getLightList());
			System.out.print("\t");
			this.printInstrument(coridor.getAcList());
	}

	private void printCorridor(Map<String, Corridor> corridors) {
		Set<String> corrs = corridors.keySet();
		Corridor c = null;
		for (String corr : corrs) {
			c = corridors.get(corr);
			System.out.print("\t");
			System.out.println(c.getCoridorName());
			System.out.print("\t");
			this.printInstrument(c.getLightList());
			System.out.print("\t");
			this.printInstrument(c.getAcList());
		}
	}

	private void printInstrument(List<Instrument> instrumentList) {
		for (Instrument instrument : instrumentList) {
			System.out.print("\t");
			System.out.print(instrument.getId());
			System.out.print(" : ");
			System.out.println(instrument.getStatus());
		}
	}

}
