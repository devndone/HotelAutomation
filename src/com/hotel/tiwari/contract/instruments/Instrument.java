package com.hotel.tiwari.contract.instruments;

public interface Instrument {

	public String getId();

	public Status getStatus();

	public void switchOn();

	public void switchOff();

	public int getPowerConsumption();

	public void setPowerConsumption(int powerConsumption);
}
