package com.masai.entities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.exceptions.BusException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.service.PassengerService;
import com.masai.utility.FileExist;

public class Transaction {



	public static void main(String[] args) throws ClassNotFoundException {
		
		Map<Integer, Buses> buses = FileExist.BusesFile();
		Map<String, Passenger> passengers = FileExist.PassengerFile();
		List<Bookings> bookings = FileExist.BookingsFile();

		Scanner sc = new Scanner(System.in);
		Passenger p = passengers.get("p@1234.com");
		
		try {
			passengerCancelBooking(sc,p.getEmail(),buses,passengers,bookings);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
	
	private static void passengerCancelBooking(Scanner sc,String email, Map<Integer, Buses> busses, Map<String, Passenger> passengers,List<Bookings> bookings) throws BusException, InvalidDetailsException {
		
		System.out.println("Enter the route id to delete your booking");
		int toDeletebooking = sc.nextInt();
		
		System.out.println("Enter BusName which you wish to cancel booking");
		String EnteredBusnumber = sc.next();
		
		int seats = 0;
		double price = 0;
		LocalDateTime cancelingtime = LocalDateTime.now();
		
		System.out.println(bookings.size());
		
		for(int i = 0 ; i < bookings.size() ; i++) {
			if(bookings.get(i).getBusName().equals(EnteredBusnumber) && bookings.get(i).getEmail().equals(email)) {
				seats += bookings.get(i).getNoofTickets();
				price += bookings.get(i).getTotal();
				bookings.remove(bookings.get(i));
			}
		}
		
		
		Buses b1 = busses.get(toDeletebooking);
		System.out.println(b1);
		b1.setSeats(b1.getSeats() + seats);
		busses.put(toDeletebooking,b1);
		
		System.out.println(busses.get(toDeletebooking));
		
		LocalDateTime departureDate = b1.getDeparture();
		
		Duration dur = Duration.between(cancelingtime, departureDate);
		long hours = dur.toHours();
		
		Passenger p = passengers.get(email);
		System.out.println(p);
		if(hours >= 24) {
			p.setWalletBalance(p.getWalletBalance() + price);
			System.out.println("You are elegible for full repayment....!");
		}
		else if(hours <= 24 && hours >= 12) {
			p.setWalletBalance(p.getWalletBalance() + price*0.5);
			System.out.println("You are elegible for 50% repayment....!");
		}
		else if(hours <= 12 && hours >= 6) {
			p.setWalletBalance(p.getWalletBalance() + price*0.2);
			System.out.println("You are elegible for 20% repayment....!");
		}
		else {
			System.out.println("you are not elegible for repayment");
		}
		
		System.out.println("Booking canceling successfull, Amount will be added to your account");
		
	}
}
