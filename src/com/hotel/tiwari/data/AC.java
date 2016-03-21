package com.hotel.tiwari.data;

import com.hotel.tiwari.common.PowerConsumptionLimit;
import com.hotel.tiwari.contract.instruments.ElectronicInstrument;
import com.hotel.tiwari.contract.instruments.Status;

public class AC extends ElectronicInstrument {

	public AC(String id, Status status) {
		super(id, status);
		if(status == Status.ON) {
			this.setPowerConsumption(PowerConsumptionLimit.MAX_AC);
		} else {
			this.setPowerConsumption(PowerConsumptionLimit.MIN_AC);
		}
	}
	
	@Override
	public synchronized void switchOn() {
		this.setStatus(Status.ON);
		this.setPowerConsumption(PowerConsumptionLimit.MAX_AC);

	}

	@Override
	public synchronized void switchOff() {
		this.setStatus(Status.OFF);
		this.setPowerConsumption(PowerConsumptionLimit.MIN_AC);

	}
}
