package com.hotel.tiwari.data;

import com.hotel.tiwari.common.PowerConsumptionLimit;
import com.hotel.tiwari.contract.instruments.ElectronicInstrument;
import com.hotel.tiwari.contract.instruments.Status;

public class Light extends ElectronicInstrument {

	public Light(String id, Status status) {
		super(id, status);
		if(status == Status.ON) {
			this.setPowerConsumption(PowerConsumptionLimit.MAX_LIGHT);
		} else {
			this.setPowerConsumption(PowerConsumptionLimit.MIN_LIGHT);
		}
	}

	@Override
	public synchronized void switchOn() {
		this.setStatus(Status.ON);
		this.setPowerConsumption(PowerConsumptionLimit.MAX_LIGHT);
	}

	@Override
	public synchronized void switchOff() {
		this.setStatus(Status.OFF);
		this.setPowerConsumption(PowerConsumptionLimit.MIN_LIGHT);
	}
}
