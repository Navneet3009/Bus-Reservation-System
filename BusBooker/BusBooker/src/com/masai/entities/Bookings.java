package com.masai.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Bookings implements Serializable{
	private String userName;
	private String email;
	private int busNumber;
	private String busName;
	private int noofTickets;
	private double price;
	private double total;
	private LocalDateTime dt;
	
	
	public Bookings() {
		
	}


	public Bookings(String userName, String email, int busNumber, String busName, int noofTickets, double price,
			double total, LocalDateTime dt) {
		super();
		this.userName = userName;
		this.email = email;
		this.busNumber = busNumber;
		this.busName = busName;
		this.noofTickets = noofTickets;
		this.price = price;
		this.total = total;
		this.dt = dt;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getBusNumber() {
		return busNumber;
	}


	public void setBusNumber(int busNumber) {
		this.busNumber = busNumber;
	}


	public String getBusName() {
		return busName;
	}


	public void setBusName(String busName) {
		this.busName = busName;
	}


	public int getNoofTickets() {
		return noofTickets;
	}


	public void setNoofTickets(int noofTickets) {
		this.noofTickets = noofTickets;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public LocalDateTime getDt() {
		return dt;
	}


	public void setDt(LocalDateTime dt) {
		this.dt = dt;
	}


	@Override
	public String toString() {
		return "Bookings [userName : " + getUserName() + ", Email : " + getEmail() + ", BusNumber : "
				+ getBusNumber() + ", BusName : " + getBusName() + ", NoofTickets : " + getNoofTickets()
				+ ", Price : " + getPrice() + ", Total : " + getTotal() + ", Dt : " + getDt() + "]";
	}
	
	
}
