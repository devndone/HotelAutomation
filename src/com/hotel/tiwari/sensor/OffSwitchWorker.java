package com.hotel.tiwari.sensor;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.hotel.tiwari.contract.instruments.Instrument;
import com.hotel.tiwari.contract.instruments.Status;
import com.hotel.tiwari.data.Corridor;
import com.hotel.tiwari.data.Hotel;

public class OffSwitchWorker extends Thread {
	private BlockingQueue<Instrument> Offsensor;
	private Hotel hotel;
	private boolean stop;

	public OffSwitchWorker(BlockingQueue<Instrument> offsensor, Hotel hotel) {
		super();
		Offsensor = offsensor;
		this.hotel = hotel;
	}

	public void run() {

		while (!isStop()) {

			/*String id = null;
			String[] idarr = null;
			String floorId = null;
			String corridorId = null;
			try {
				id = Offsensor.take();
				idarr = id.split("-");
				floorId = idarr[0].trim();
				corridorId = idarr[1].trim();
				if(floorId != null && !floorId.isEmpty() && corridorId != null && !corridorId.isEmpty()) {
					System.out.println("Floor Id : " + floorId + " CorridorId : " + corridorId);
					Corridor coridor = hotel.getFloors().get(floorId).getSubCorridors().get(corridorId);
					List<Instrument> acList = coridor.getAcList();
					List<Instrument> lightList = coridor.getLightList();
					this.switchOff(acList);
					this.switchOff(lightList);
		
					synchronized (System.out) {
						System.out.println("No Movement detected in coridor -> "
								+ coridor.getCoridorId());
						hotel.printHotelStatus();
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			try {
				this.switchOff(Offsensor.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	private void switchOff(Instrument instrument) {
		instrument.switchOff();
	}

	private void switchOff(List<Instrument> instrumentList) {
		for (Instrument instrument : instrumentList) {
			instrument.switchOff();
		}
	}
	
	private boolean isStop() {
		return stop;
	}

	public void doStop(boolean doStop) {
		this.stop = doStop;
	}
}
