package com.masai.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.masai.entities.Bookings;
import com.masai.entities.Buses;
import com.masai.entities.Passenger;
import com.masai.exceptions.BusException;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;

public class PassengerServiceImpl implements PassengerService{

	@Override
	public boolean login(String email, String password, Map<String, Passenger> pasenger)
			throws InvalidDetailsException {
		if(pasenger.containsKey(email)) {
			if(pasenger.get(email).getPass().equals(password)) {
				return true;
			}
			else {
				throw new InvalidDetailsException("Invalid Credentials");
			}
		}
		else {
			throw new InvalidDetailsException("You have not sign up yet, please Signup to explore the features");
		}
	}

	@Override
	public void signup(Passenger pas, Map<String, Passenger> pasenger) throws DuplicateDataException {
		// TODO Auto-generated method stub
		if(pasenger.containsKey(pas.getEmail())) {
			throw new DuplicateDataException("Passenger account already Exist, Please login");
		}
		else {
			pasenger.put(pas.getEmail(),pas);
		}
	}
	
	@Override
	public void updatePassengerDetails(Passenger p, String email, Map<String, Passenger> pasenger,
			List<Bookings> bokings, PassengerService passenger) throws InvalidDetailsException {
		Passenger pass = pasenger.get(email);
		
		pass.setName(p.getName());
		pass.setAddress(p.getAddress());
		pass.setPass(p.getPass());
		pasenger.put(email, pass);
		
		
		// updating the booking list;
		
		
		for(Bookings b : bokings) {
			if(b.getEmail().equals(email)) {
				b.setUserName(p.getName());
				b.setEmail(p.getEmail());
			}
		}
		
	}

	
	@Override
	public boolean addMoneytoWallet(double amout, String email, Map<String, Passenger> pasenger)
			throws InvalidDetailsException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double viewWalletBalance(String email, Map<String, Passenger> pasenger) throws InvalidDetailsException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Passenger viewPassengerDetails(String email, Map<String, Passenger> pasenger)
			throws InvalidDetailsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Passenger> viewAllPassenger(Map<String, Passenger> pasenger) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean buyTickets(int id, int noSeats, String Email, Map<Integer, Buses> buses,
			Map<String, Passenger> pasenger, List<Bookings> bookings) throws InvalidDetailsException, BusException {
		
		if(buses.size() == 0) {
			throw new BusException("Buses list is empty");
		}
		
		if(buses.containsKey(id)) {
			Buses mybus = buses.get(id);
			
			if(mybus.getSeats() > noSeats) {
				Passenger pas = pasenger.get(Email);
				
				double buyingPrice = noSeats * mybus.getPrice();
				
				
				if(pas.getWalletBalance() >= buyingPrice) {
					
					pas.setWalletBalance(pas.getWalletBalance() - buyingPrice);
					
					mybus.setSeats(mybus.getSeats() - noSeats);
					
					buses.put(id, mybus);
					
					Bookings boks = new Bookings(pas.getName(),pas.getEmail(),mybus.getId(),mybus.getBusName(),noSeats,mybus.getPrice(),buyingPrice,LocalDateTime.now());
					
					System.out.println(boks);
					bookings.add(boks);
					
				}
				else {
					throw new InvalidDetailsException("Insufficent wallet balance please reacharge");
				}
			}
			else {
				throw new InvalidDetailsException("No of seats in the bus are not sufficent please select less no of seats");
			}
		}
		else {
			throw new InvalidDetailsException("No bus is available at the given route id");
		}
		
		return false;
	}

}
