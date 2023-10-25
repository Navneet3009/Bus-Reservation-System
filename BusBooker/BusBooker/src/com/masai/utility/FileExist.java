package com.masai.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.masai.entities.Bookings;
import com.masai.entities.Buses;
import com.masai.entities.Passenger;

public class FileExist {
	public static Map<Integer, Buses> BusesFile() {
		Map<Integer, Buses> busfile = new LinkedHashMap<>();

		File f = new File("Buses.ser");
		boolean flag = false;
		try {
			if (!f.exists()) {
				f.createNewFile();
				flag = true;
			}
			if (flag) {
				busfile = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(busfile);
				return busfile;
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				busfile = (Map<Integer, Buses>) ois.readObject();
				return busfile;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return busfile;
	}

	public static Map<String, Passenger> PassengerFile() {
		Map<String, Passenger> passengerFile = new LinkedHashMap<>() ;
		
		File f = new File("Passenger.ser");
		boolean flag = false;
		try {
			if(!f.exists()) {
				f.createNewFile();
				flag = true;
			}
			if(flag) {
				passengerFile = new LinkedHashMap<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(passengerFile);
				return passengerFile;
			}
			else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				passengerFile = (Map<String, Passenger>) ois.readObject();
				return passengerFile;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return passengerFile;
	}
	
	public static List<Bookings> BookingsFile(){
		
		List<Bookings> bookingsFile = new ArrayList<>();
		
		File f = new File("Bookings.ser");
		boolean flag = false;
		try {
			if(!f.exists()) {
				f.createNewFile();
				flag = true;
			}
			if(flag) {
				bookingsFile = new ArrayList<>();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				oos.writeObject(bookingsFile);
				return bookingsFile;
			}
			else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				bookingsFile = (List<Bookings>) ois.readObject();
				return bookingsFile;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return bookingsFile;
	}
}
