package com.hotel.tiwari.data;

import java.util.Collections;
import java.util.List;

import com.hotel.tiwari.contract.instruments.CoridorType;
import com.hotel.tiwari.contract.instruments.Instrument;

public class Corridor {

	private String coridorId;
	private String coridorName;
	private CoridorType coridorType;

	private List<Instrument> acList;
	private List<Instrument> lightList;

	public Corridor(String coridorName, String coridorId,
			CoridorType coridorType, List<Instrument> acList,
			List<Instrument> lightList) {
		super();
		this.coridorId = coridorId;
		this.coridorName = coridorName;
		this.coridorType = coridorType;
		this.acList = acList;
		this.lightList = lightList;
	}

	public String getCoridorName() {
		return coridorName;
	}

	public CoridorType getCoridorType() {
		return coridorType;
	}

	public List<Instrument> getAcList() {
		return Collections.unmodifiableList(acList);
	}

	public List<Instrument> getLightList() {
		return Collections.unmodifiableList(lightList);
	}

	public String getCoridorId() {
		return coridorId;
	}

}
