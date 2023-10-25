package com.masai.service;

import java.util.List;

import com.masai.entities.Bookings;
import com.masai.exceptions.BookingException;

public interface BookingService {
	public void ViewallBookings(List<Bookings> bookings) throws BookingException;
	public void ViewallBookingsDateWise();
	public void ViewBoookingByPassengerName(String email, List<Bookings> bookings) throws BookingException;
	public void ViewBookingBussName(String BusName, List<Bookings> bookings) throws BookingException;
}
