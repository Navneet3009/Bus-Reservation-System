package com.masai.main;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Bookings;
import com.masai.entities.Buses;
import com.masai.entities.Passenger;
import com.masai.exceptions.BookingException;
import com.masai.exceptions.BusException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.service.BookingService;
import com.masai.service.BookingServiceImpl;
import com.masai.service.BusesService;
import com.masai.service.BusesServiceImpl;
import com.masai.service.PassengerService;
import com.masai.service.PassengerServiceImpl;
import com.masai.utility.Admin;
import com.masai.utility.FileExist;
import com.masai.utility.IDGeneration;

public class Main {
	
	//<------------------------------------------- ADMIN FUNCTIONS ------------------------------------------------------>
	public static String adminAddBus(Scanner sc, Map<Integer, Buses> buses, BusesService busesService) {
		String str = null;
		System.out.println("enter the details of new bus");

		System.out.println("Enter 6 digit route id");
		int routeid = sc.nextInt();

		System.out.println("enter bus Name");
		String busName = sc.next();

		System.out.println("enter the source");
		String source = sc.next();

		System.out.println("enter the destination");
		String destination = sc.next();

		System.out.println("enter the departure date in dd/mm/yyyyHH:MM:SS formate");
		String departure = sc.next();

		System.out.println("enter the arival date in dd/mm/yyyyHH:MM:SS formate");
		String arival = sc.next();

		System.out.println("enter the busType");
		String busType = sc.next();

		System.out.println("enter the no of seats");
		int seats = sc.nextInt();
		
		System.out.println("Enter the price of the ticket per seat");
		double price = sc.nextDouble();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm:ss");
		LocalDateTime dateTime1 = LocalDateTime.parse(departure, formatter);
		LocalDateTime dateTime = LocalDateTime.parse(arival, formatter);

		Buses newBus = new Buses(IDGeneration.generateID(),busName,busType,seats,source,destination,dateTime1,dateTime,routeid,price);

		str = busesService.addBus(newBus, buses, routeid);

		return str;
	}
	
	public static void adminLogin(Scanner sc) throws InvalidDetailsException {

		System.out.println("Enter the user name");
		String userName = sc.next();
		System.out.println("Enter the password");
		String password = sc.next();

		if (userName.equals(Admin.username) && password.equals(Admin.password)) {
			System.out.println("Successfully logged in....!");
		} else {
			throw new InvalidDetailsException("Invalid Admin Credentials");
		}
	}

	private static void adminViewAllBuses(Map<Integer, Buses> buses, BusesService busService) throws BusException {
		if(buses != null && buses.size() > 0) {
			busService.ViewAllBusses(buses);
		}
		else {
			throw new BusException("Buses List is empty......!");
		}
	}

	private static void admnViewallBookingsByBusname(Scanner sc, List<Bookings> bookings, BookingService bookService)
			throws BookingException {
		// TODO Auto-generated method stub
		System.out.println("please enter the busName which you wish to search");
		String BusName = sc.next();

		bookService.ViewBookingBussName(BusName, bookings);
	}

	private static void adminViewallBooking(Scanner sc, List<Bookings> bookings, BookingService bookService)
			throws BookingException {
		// TODO Auto-generated method stub
		bookService.ViewallBookings(bookings);
	}

	private static void adminViewBookingByUesrName(Scanner sc, List<Bookings> bookings, BookingService bookService){
		// TODO Auto-generated method stub
		System.out.println("enter the name of the costumer to see his bookings");
		String name = sc.next();
		try {
			bookService.ViewBoookingByPassengerName(name, bookings);
		} catch (BookingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private static String adminUpdateBusDetails(Scanner sc, Map<Integer, Buses> buses,BusesService busService2)
			throws BusException {
		String res = null;

		System.out.println("Please enter the routeid of the bus which you wish to be updated");
		int routeid = sc.nextInt();

		System.out.println("Enter new deteails below to updated bus");
		System.out.println("new bus name to be updated\n");
		String Busname = sc.next();

		System.out.println("new Bus type");
		String bustype = sc.next();

		System.out.println("new no of seats to be updated");
		int NoOfSeats = sc.nextInt();

		Buses b1 = new Buses(Busname, bustype, NoOfSeats);
		
		
		String oldName = buses.get(routeid).getBusName();
		
		res = busService2.updateBus(routeid,b1,buses);

		return res;
	}

	private static void adminDeleteBus(Scanner sc, Map<Integer, Buses> busses, BusesService busService)
			throws BusException {
		
		System.out.println("please enter the id of Bus to be deleted");
		int id = sc.nextInt();
		
		if(busses != null && busses.size() > 0) {
			busService.deleteBus(id, busses);
		}
		else {
			throw new BusException("Bus list is empty...!");
		}
		
	}

    private static void adminViewBookingByDateRange(Scanner sc, List<Bookings> bookings) {
    	System.out.println("enter Start date in the formate dd/MM/yyyyHH:mm:ss");
    	String start = sc.next();
    	
    	System.out.println("enter end date formate dd/MM/yyyyHH:mm:ss");
    	String end = sc.next();
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyyHH:mm:ss");
    	LocalDateTime startDate = LocalDateTime.parse(start, formatter);
    	LocalDateTime endDate = LocalDateTime.parse(end, formatter);
    	
    	for (Bookings booking : bookings) {
    	    LocalDateTime bookingDate = booking.getDt();
    	    if (bookingDate.isAfter(startDate) && bookingDate.isBefore(endDate)) {
    	        System.out.println(booking);
    	    }
    	}
	}
	
	public static void adminFunctionality(Scanner sc, Map<Integer, Buses> buses, Map<String, Passenger> passengers,
			List<Bookings> bookings) throws InvalidDetailsException, BookingException, BusException {
		adminLogin(sc);
		BookingService bookService = new BookingServiceImpl();
		BusesService busService = new BusesServiceImpl();
		PassengerService pasenService = new PassengerServiceImpl();
		int choice = 0;
		try {
			
			do {
				System.out.println("Press 1 -> Add the bus");
				System.out.println("Press 2 -> view all buses");
				System.out.println("Press 3 -> Delete the bus");
				System.out.println("Press 4 -> Update the bus details");
				System.out.println("Press 5 -> View passenger details by name");
				System.out.println("Press 6 -> View all booking");
				System.out.println("Press 7 -> View booking by Bus Name");
				System.out.println("Press 8 -> view bookings with the date range");
				System.out.println("press 9 -> To Log Out");

				choice = sc.nextInt();

				switch (choice) {
				case 1:
					String added = adminAddBus(sc, buses, busService); //done
					System.out.println(added);
					break;
				case 2:
					try{
						adminViewAllBuses(buses,busService); //done
					}
					catch(Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3:
					try {
						adminDeleteBus(sc,buses,busService); // done
					}catch(Exception e) {
						System.out.println(e.getMessage());
					}
					
					break;
				case 4:
					String changedDetails = adminUpdateBusDetails(sc,buses,busService); //done
					System.out.println(changedDetails);
					break;
				case 5:
					adminViewBookingByUesrName(sc,bookings,bookService);//done
					break;
				case 6:
					adminViewallBooking(sc,bookings,bookService); // done
					break;
				case 7:
					admnViewallBookingsByBusname(sc,bookings,bookService);
					break;
				case 8:
					adminViewBookingByDateRange(sc,bookings);
					break;
				case 9:
					System.out.println("successfully loged out from the system");
					break;
				default:
					System.out.println("wrong choice");
					break;
				}
			} 
			while (choice <= 8);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

//<-------------------------------------------MAIN METHOD----------------------------------------------------------------->
	public static void main(String[] args) {

		Map<Integer, Buses> buses = FileExist.BusesFile();
		Map<String, Passenger> passengers = FileExist.PassengerFile();
		List<Bookings> bookings = FileExist.BookingsFile();

		Scanner sc = new Scanner(System.in);

		System.out.println("Welcome..! to Bus Ticket Reservation System \n");

		try {
			int preference = 0;

			do {
				System.out.println(
						"Please enter your preference,\n 1 --> Admin Login. \n 2 --> Passenger Login.\n 3 --> Passenger Sign Up.\n 0 --> To close the application.");
				preference = sc.nextInt();

				switch (preference) {
				case 1:
					adminFunctionality(sc, buses, passengers, bookings);
					break;
				case 2:
					passengersFunctionality(sc, buses, passengers, bookings);
					break;
				case 3:
					passengerSignup(sc, passengers);
					break;
				case 0:
					System.out.println("successfully existed from the system");
					break;

				default:
					throw new IllegalArgumentException("Invalid Selection");
				}
			} while (preference != 0);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			try {
				ObjectOutputStream boos = new ObjectOutputStream(new FileOutputStream("Buses.ser"));
				boos.writeObject(buses);
				ObjectOutputStream poos = new ObjectOutputStream(new FileOutputStream("Passenger.ser"));
				poos.writeObject(passengers);

				ObjectOutputStream bkoos = new ObjectOutputStream(new FileOutputStream("Bookings.ser"));
				bkoos.writeObject(bookings);
			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println(e2.getMessage());
			}
		}
	}
	
	//<-------------------------------------------- PASSENGER FUNCTIONS ------------------------------------------->
	public static void passengersFunctionality(Scanner sc, Map<Integer, Buses> busses,
		Map<String, Passenger> passengers, List<Bookings> bookings) throws InvalidDetailsException, BookingException, InvalidDetailsException{
		BookingService bookService = new BookingServiceImpl();
		BusesService busService = new BusesServiceImpl();
		PassengerService pasenService = new PassengerServiceImpl();
		
		
		//passenger login--->
		System.out.println("Please enter the following details to login");
		System.out.println("Enter the email");
		String email = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		
		if(passengerLogin(email,pass,passengers,pasenService)) {
			try {
				int choice = 0;
				do {
					System.out.println("select the choice you wish");
					System.out.println("Press 1 -> TO view all Buses");
					System.out.println("Press 2 -> To book ticket");
					System.out.println("Press 3 -> To cancel booking");
					System.out.println("Press 4 -> To Edit Passenger Details");
					System.out.println("Press 5 -> To See all Bookings");
					System.out.println("Press 6 -> To delete Passenger Account");
					System.out.println("Press 7 -> To view Account");
					System.out.println("Press 8 -> To Top Up Wallet");
					System.out.println("Press 9 -> TO Log Out.....!");
					
					choice = sc.nextInt();
					
					switch(choice) {
					case 1:
						passengerViesAllBuses(busses,busService);
						break;
					case 2:
						String ticket = passengerBookTicket(sc,email,busses,passengers,bookings,pasenService);
						System.out.println(ticket);
						System.out.println("Please note route Id and bus-Id in case if you have to cancel booking");
						break;
					case 3:
						try {
							passengerCancelBooking(sc,email,busses,passengers,bookings,pasenService);
						}catch(Exception e) {
							System.out.println(e.getMessage());
						}
						break;
					case 4:
						passengerEditDetails(sc,passengers,bookings,pasenService);
						break;
					case 5:
						passengerViewAllBooking(email,bookings);
						break;
					case 6:
						passengerDeletehisAccount(sc,email,passengers);
						break;
					case 7:
						viewAccount(email,passengers);
						break;
					case 8:
						topUpWallet(sc,passengers);
						System.out.println("Wallet top up successfull......!");
					case 9:
						System.out.println("Loged out successfully...!");
						break;
					default:
						System.out.println("Invalid choice");
						break;
					}
				}
				while(choice < 9);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

	private static void topUpWallet(Scanner sc, Map<String, Passenger> passengers) {
		System.out.println("enter your email address");
		String email = sc.next();
		
		System.out.println("enter the amount you wish to add to your wallet");
		double amount = sc.nextDouble();
		
		Passenger p = passengers.get(email);
		p.setWalletBalance(p.getWalletBalance() + amount);
		
		
		
	}

	private static void passengerDeletehisAccount(Scanner sc, String email, Map<String, Passenger> passengers) {
		System.out.println("press 1 -> To conform Delete your account permanently");
		int choice = sc.nextInt();
		
		if(choice == 1) {
			passengers.remove(email);
		}
	}

	private static void viewAccount(String email, Map<String, Passenger> passengers) {
		// TODO Auto-generated method stub
		System.out.println(passengers.get(email));
	}

	private static void passengerCancelBooking(Scanner sc,String email, Map<Integer, Buses> busses, Map<String, Passenger> passengers,List<Bookings> bookings,
			PassengerService pasenService) throws BusException, InvalidDetailsException {
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

	private static void passengerViewAllBooking(String email, List<Bookings> bookings) {
		for(Bookings b : bookings) {
			if(b.getEmail().equals(email)) {
				System.out.println(b);
			}
		}
	}

	private static String passengerEditDetails(Scanner sc, Map<String, Passenger> passengers,List<Bookings> bookings,
			PassengerService pasenService) {
		
		System.out.println("Enter your email id");
		String email = sc.next();
		
		System.out.println("Enter your password");
		String oldpassword = sc.next();
		
		try {
			if(passengers.containsKey(email) && passengers.get(email).getPass().equals(oldpassword)) {
				
				System.out.println("Account verifed successfully....!");
				
				System.out.println("Enter the new details below");
				
				System.out.println("Enter New Name");
				String newname = sc.next();
				
				System.out.println("Enter New Password");
				String newpassword = sc.next();
				
				System.out.println("Enter the new Address");
				String newAddress = sc.next();
				
				Passenger p1 = new Passenger(newname,newpassword,newAddress);
				
				pasenService.updatePassengerDetails(p1,email, passengers, bookings, pasenService);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return "Passenger details are updated successfully....!";
		
	}

	private static String passengerBookTicket(Scanner sc, String email, Map<Integer, Buses> busses,
			Map<String, Passenger> passengers, List<Bookings> bookings, PassengerService pasenService) {
		// TODO Auto-generated method stub
		System.out.println("Enter route-Id of the bus to buy tickets");
		int routeID = sc.nextInt();
		
		System.out.println("Enter the number of tickets you want to buy");
		int quantityOfSeats = sc.nextInt();
		
		try {
			pasenService.buyTickets(routeID, quantityOfSeats, email, busses, passengers, bookings);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "Transaction successfull ticket are booked";
	}

	private static void passengerViesAllBuses(Map<Integer, Buses> busses, BusesService busService) {
		// TODO Auto-generated method stub
		try {
			busService.ViewAllBusses(busses);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	private static boolean passengerLogin(String email, String pass, Map<String, Passenger> passengers,
			PassengerService pasenService) {
		// TODO Auto-generated method stub
		try {
			pasenService.login(email, pass, passengers);
			System.out.println("Passenger has loged in succesfully");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return false;
	}

	public static void passengerSignup(Scanner sc, Map<String, Passenger> passengers) {
		
		System.out.println("Please enter the following detals to signup");
		System.out.println("Please Passenger Name");
		String name = sc.next();
		
		System.out.println("Enter the password");
		String pass = sc.next();
		
		System.out.println("Enter your address");
		String address = sc.nextLine();
		sc.nextLine();
		
		System.out.println("Enter your Email id");
		String email = sc.next();
		
		System.out.println("Please Enter the amount to added in to your wallet");
		double walletAmount = sc.nextDouble();
		
		Passenger pas = new Passenger(walletAmount,name,pass,address,email);
		
		PassengerService pasenService = new PassengerServiceImpl();
		try {
			pasenService.signup(pas, passengers);
			System.out.println("Passenger Sign Up successfull...!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
