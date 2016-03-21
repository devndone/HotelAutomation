package com.hotel.tiwari.contract.instruments;


public abstract class ElectronicInstrument implements Instrument {

	private String id;
	private volatile Status status;
	private int powerConsumption;

	public ElectronicInstrument(String id, Status status) {
		this.id = id;
		this.status = status;
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	@Override
	public Status getStatus() {
		return this.status;
	}
	
	protected void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public int getPowerConsumption() {
		return this.powerConsumption;
	}

	@Override
	public void setPowerConsumption(int powerConsumption) {
		this.powerConsumption = powerConsumption;
	}
}
