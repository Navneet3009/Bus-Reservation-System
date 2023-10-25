package com.masai.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


public class Buses implements Serializable{
	private int id;
	private String busName;
	private String source;
	private String destination;
	private String busType;
	private LocalDateTime departure;
	private LocalDateTime arival;
	private int seats;
	private int RouteId;
	private double price;
	
	public Buses(int id, String busName, String busType, int seats,String source, String destination,LocalDateTime departure,
			LocalDateTime arival,int routeid,double price) {
		super();
		this.id = id;
		this.busName = busName;
		this.busType = busType;
		this.seats = seats;
		this.source = source;
		this.destination = destination;
		this.departure = departure;
		this.arival = arival;
		this.RouteId = routeid;
		this.price = price;
	}
	
	public Buses(String busname2, String bustype2, int noOfSeats) {
		this.busName = busname2;
		this.busType = bustype2;
		this.seats = noOfSeats;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int getRouteId() {
		return RouteId;
	}
	public void setRouteId(int routeID) {
		this.RouteId = routeID;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public LocalDateTime getDeparture() {
		return departure;
	}
	public void setDeparture(LocalDateTime departure) {
		this.departure = departure;
	}
	public LocalDateTime getArival() {
		return arival;
	}
	public void setArival(LocalDateTime arival) {
		this.arival = arival;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	@Override
	public int hashCode() {
		return Objects.hash(arival, busName, busType, departure, destination, id, seats, source);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Buses other = (Buses) obj;
		return Objects.equals(arival, other.arival) && Objects.equals(busName, other.busName)
				&& Objects.equals(busType, other.busType) && Objects.equals(departure, other.departure)
				&& Objects.equals(destination, other.destination) && id == other.id && seats == other.seats
				&& Objects.equals(source, other.source);
	}
	@Override
	public String toString() {
		return "Buses [BusId : " + getId() + ", BusName : " + getBusName() +", ROute Id : "+ getRouteId() +", Source : " + getSource()
				+ ", Destination : " + getDestination() + ", BusType : " + getBusType() + ", Departure : "
				+ getDeparture() + ", Arival : " + getArival() + ", NoOFSeats : " + getSeats() + "]";
	}

	
}
