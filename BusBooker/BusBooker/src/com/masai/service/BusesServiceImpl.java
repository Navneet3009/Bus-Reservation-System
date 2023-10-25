package com.masai.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.masai.entities.Bookings;
import com.masai.entities.Buses;
import com.masai.exceptions.BusException;
import com.masai.utility.IDGeneration;

public class BusesServiceImpl implements BusesService{

	@Override
	public String addBus(Buses bus, Map<Integer, Buses> buses,int routeID) {
		// TODO Auto-generated method stub
		buses.put(routeID, bus);
		System.out.println(buses);
		return "Bus Added Successfully";
	}

	@Override
	public void ViewAllBusses(Map<Integer, Buses> buses) throws BusException {
		// TODO Auto-generated method stub
		if(buses != null && buses.size() > 0) {
			for(Map.Entry<Integer, Buses> bus : buses.entrySet()) {
				System.out.println(bus.getValue());
			}
		}
		else {
			throw new BusException("Buses list is empty please add some buses");
		}
	}

	@Override
	public void deleteBus(int id, Map<Integer, Buses> buses) throws BusException {
		// TODO Auto-generated method stub
		if(buses != null && buses.size() > 0) {
			if(buses.containsKey(id)) {
				buses.remove(id);
				System.out.println("Bus has been deleted Successfully");
			}
			else {
				throw new BusException("bus not found");
			}
		}
		else {
			throw new BusException("Buses list is empty");
		}
	}

	@Override
	public String updateBus(int id,Buses bus, Map<Integer, Buses> buses) throws BusException {
		// TODO Auto-generated method stub
		if(buses != null && buses.size() > 0) {
			if(buses.containsKey(id)) {
				Buses b1 = buses.get(id);
				b1.setBusName(bus.getBusName());
				b1.setBusType(bus.getBusType());
				b1.setSeats(bus.getSeats());
				
				buses.put(id,b1);
			}
			else {
				throw new BusException("Bus not found");
			}
		}
		else {
			throw new BusException("Bus list is empty");
		}
		
		return "bus updated successfully";
	}

}
