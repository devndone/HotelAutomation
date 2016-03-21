package com.hotel.tiwari.sensor;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.hotel.tiwari.common.PowerConsumptionLimit;
import com.hotel.tiwari.contract.instruments.Instrument;
import com.hotel.tiwari.data.Corridor;
import com.hotel.tiwari.data.Floor;
import com.hotel.tiwari.data.Hotel;

public class SensorOrchestrator implements Runnable {

	private Hotel hotel;
	private BlockingQueue<Instrument> onSensor = new LinkedBlockingQueue<Instrument>();
	private BlockingQueue<Instrument> offSensor = new LinkedBlockingQueue<Instrument>();
	private Boolean stop = false;
	private Scanner scanner;

	public SensorOrchestrator(final Hotel hotel, final Scanner scanner) {
		this.hotel = hotel;
		this.scanner = scanner;
	}

	public void passOnSensor(Instrument instrument) {
		//onSensor.add(floorId + "-" + corridorId + "-" + instrumentId);
		onSensor.add(instrument);
	}

	public void passOffSensor(Instrument instrument) {
		//offSensor.add(floorId + "-" + corridorId + "-" + instrumentId);
		offSensor.add(instrument);
	}

	@Override
	public void run() {

		//OnSwitchWorker onWorker = new OnSwitchWorker(onSensor, hotel);
		//OffSwitchWorker offWorker = new OffSwitchWorker(offSensor, hotel);

		//onWorker.start();
		//offWorker.start();
		
		Map<String, Floor> flrs = null;
		Map<String, Corridor> cors = null;
		Map<String, Stack<Instrument>> floorIns = new HashMap<>();
		Random randomizer = new Random();
		boolean movementDetected = false;
		while(!isStop()) {
			flrs = this.hotel.getFloors();
			for(Entry<String, Floor> e : flrs.entrySet()) {
				if(!floorIns.containsKey(e.getKey()) || floorIns.get(e.getKey()) == null) {
					floorIns.put(e.getKey(), new Stack<Instrument>());
				}
				cors = e.getValue().getSubCorridors();
				for(Entry<String, Corridor> c : cors.entrySet()) {
					movementDetected = this.movementDetector(scanner, 60L, e.getValue(), c.getValue());
					this.postMovementProcessor(movementDetected, e.getValue(), c.getValue(), floorIns, randomizer);
					this.printHotelStatus();
				}
			}
		}

	}
	
	private void printHotelStatus() {
		this.hotel.printHotelStatus();
	}
	
	protected void postMovementProcessor(boolean movementDetected, Floor floor, Corridor corridor, Map<String, Stack<Instrument>> floorIns, Random randomizer) {
		Instrument insl = null, insa = null;
		int difffpocons = 0;
		if(movementDetected) {
			insl = corridor.getLightList().get(randomizer.nextInt((corridor.getLightList().size())));
			insl.switchOn();
			//this.passOnSensor(insl);
			difffpocons = PowerConsumptionLimit.calculateFloorPowerConsumptionDisparity(floor);
			if(difffpocons < 0) {
				insa = corridor.getAcList().get(randomizer.nextInt((corridor.getAcList().size())));
				insa.switchOff();
				//this.passOffSensor(insa);
				floorIns.get(floor.getFloorName()).push(insa);
			}
		} else {
			insl = corridor.getLightList().get(randomizer.nextInt((corridor.getLightList().size())));
			insl.switchOff();
			//this.passOffSensor(insl);
			difffpocons = PowerConsumptionLimit.calculateFloorPowerConsumptionDisparity(floor);
			if(difffpocons > 0 && !floorIns.get(floor.getFloorName()).isEmpty()) {
				insa = floorIns.get(floor.getFloorName()).pop();
				insa.switchOn();
				//this.passOnSensor(insa);
			}
		}
	}
	
	protected boolean movementDetector(final Scanner scanner, long waitDurationInSeconds, final Floor floor, final Corridor corridor) {
		long currentTime = System.currentTimeMillis();
		long timeToSLeep = (waitDurationInSeconds*1000);//(60Seconds*1000Miliseconds=1Minute)/10
		long waitTimeForDetectingMovement = currentTime + timeToSLeep;
		System.out.println("Please pres Y to suggest movement or any other key to suggest non-movement, on Floor " + floor.getFloorName() + "'s Corridor " + corridor.getCoridorId() + " !");
		String sin = null;
		boolean movementDetected = false;
		while(waitTimeForDetectingMovement >= currentTime) {
			sin = scanner.next();
			/*try {
				Thread.sleep(timeToSLeep);
			} catch (InterruptedException e) {
				//e.printStackTrace();
			}*/
			if(sin != null && !sin.isEmpty()) {
				if(sin.equalsIgnoreCase("Y")) {
					movementDetected = true;
				}
				break;
			}
			currentTime = System.currentTimeMillis();
		}
		return movementDetected;
	}
	
	private boolean isStop() {
		return stop;
	}

	public void doStop(boolean doStop) {
		this.stop = doStop;
	}

}
