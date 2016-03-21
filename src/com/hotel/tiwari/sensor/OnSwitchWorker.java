package com.hotel.tiwari.sensor;

import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.hotel.tiwari.contract.instruments.Instrument;
import com.hotel.tiwari.contract.instruments.Status;
import com.hotel.tiwari.data.Corridor;
import com.hotel.tiwari.data.Hotel;

public class OnSwitchWorker extends Thread {
	private BlockingQueue<Instrument> Onsensor;
	private Hotel hotel;
	private boolean stop;

	public OnSwitchWorker(BlockingQueue<Instrument> onsensor, Hotel hotel) {
		super();
		Onsensor = onsensor;
		this.hotel = hotel;
	}

	public void run() {

		while (!isStop()) {

			/*String id = null;
			String[] idarr = null;
			String floorId = null;
			String corridorId = null;
			String instrumentId = null;
			try {
				id = Onsensor.take();
				idarr = id.split("-");
				floorId = idarr[0].trim();
				corridorId = idarr[1].trim();
				if(floorId != null && !floorId.isEmpty() && corridorId != null && !corridorId.isEmpty()) {
					System.out.println("Floor Id : " + floorId + " CorridorId : " + corridorId + " instrumentId : " + instrumentId);
					Corridor corridor = hotel.getFloors().get(floorId).getSubCorridors().get(corridorId);
					List<Instrument> acList = corridor.getAcList();
					List<Instrument> lightList = corridor.getLightList();
					this.switchOn(acList);
					this.switchOn(lightList);
					synchronized (System.out) {
						//System.out.println("Movement detected in corridor -> "
						//		+ corridor.getCoridorId());
						hotel.printHotelStatus();
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
			try {
				this.switchOn(Onsensor.take());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	private void switchOn(Instrument instrument) {
		instrument.switchOn();
	}

	private void switchOn(List<Instrument> instrumentList) {
		for (Instrument instrument : instrumentList) {
			instrument.switchOn();
		}
	}
	
	private boolean isStop() {
		return stop;
	}

	public void doStop(boolean doStop) {
		this.stop = doStop;
	}

}
