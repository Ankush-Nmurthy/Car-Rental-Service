package com.masai.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import com.masai.dao.BookingsImpl;
import com.masai.dao.EMUtilities;
import com.masai.dao.IBooking;
import com.masai.entity.Cars;
import com.masai.entity.Customer;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.service.BookingServiceImp;
import com.masai.service.CarServiceImpl;
import com.masai.service.CustomerServiceImpl;
import com.masai.service.IBookingService;
import com.masai.service.ICarService;
import com.masai.service.ICustomerService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CustomerUI {
	public static void customerRegistration(Scanner sc) {
		System.out.print("Enter your name : ");
		String name = sc.next();
		System.out.print("Enter your user-name : ");
		String username = sc.next();
		System.out.print("Enter your password : ");
		String password = sc.next();
		System.out.print("Enter your Location : ");
		String location = sc.next();
		System.out.print("Enter your Age : ");
		int Age = sc.nextInt();

		Customer customer = new Customer(name, username, password, Age, location, null);

		try {
			ICustomerService ics = new CustomerServiceImpl();
			ics.addCustomer(customer);
			System.out.println("Customer added successfully..");
		} catch (SomethingWentWrongException e) {
			System.out.println(e);
		}
	}

	public static void DisplayCustomerMenu() {
		System.out.println("1. View all cars");
		System.out.println("2. View detail of a single car using carID");
		System.out.println("3. Search cars");
		System.out.println("4. Book Car");
		System.out.println("5. DeleteBooking");
//		System.out.println("6. Modify the existing booking");
		System.out.println("6. View all transaction ");
		System.out.println("7. Delete account.");
		System.out.println("0. LogOut");
	}

	public static void userLogin(Scanner sc) {
		System.out.print("Enter username : ");
		String username = sc.next();
		System.out.print("Enter Password : ");
		String password = sc.next();

		try {
			ICustomerService customerService = new CustomerServiceImpl();
			customerService.login(username, password);
			userMenu(sc);
		} catch (NoRecordFoundException | SomethingWentWrongException p) {
			System.out.println(p.getMessage());
		}
	}

	public static void changePassword(Scanner sc) {
		System.out.print("Enter Current or Old-password : ");
		String oldPassword = sc.next();
		System.out.print("Enter new password : ");
		String newPassword = sc.next();
		System.out.print("Re-Enter new password : ");
		String reEnteredPassword = sc.next();

		if (!newPassword.equals(reEnteredPassword)) {
			System.out.println("New password and Re-entered password mismatched.");
			return;
		} else if (newPassword.equals(oldPassword)) {
			System.out.println("New password should be different.");
			return;
		}

		try {
			ICustomerService customerService = new CustomerServiceImpl();
			customerService.changePassword(oldPassword, reEnteredPassword);
			System.out.println("Password updated Successfully.");
		} catch (SomethingWentWrongException p) {
			System.out.println(p.getMessage());
		}
	}

	public static void deleteAccount(Scanner sc) {
		System.out.print("press [y] in case if you want delete your account else press [n] : ");
		char choice = sc.next().toLowerCase().charAt(0);
		if (choice == 'y') {
			try {
				ICustomerService customerService = new CustomerServiceImpl();
				customerService.deleteAccount();
				System.out.println("Your Account has been deleted successfully, we hope to see you soon.");
			} catch (SomethingWentWrongException s) {
				System.out.println(s.getMessage());
			}
		}
	}

//----------------------------- searching methods which should be added to DAO layer-----------------------------------------
	public static List<Object[]> getCarList(String carname) {
		List<Object[]> carlist = null;
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Query query = em.createQuery(
					"SELECT p.carId,p.model,c.companyName, p.availability, p.mileage, p.year, p.Location, p.amount FROM Cars p JOIN CarCompany c ON p.carCompany = c where c.companyName = :companyName");
			query.setParameter("companyName", carname);
			carlist = query.getResultList();
		} catch (PersistenceException p) {
			System.out.println(p);
		}
		return carlist;
	}

	public static List<Object[]> getCarByModel(String model) {
		List<Object[]> carlist = null;
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Query query = em.createQuery(
					"SELECT p.carId,p.model,c.companyName, p.availability, p.mileage, p.year, p.Location, p.amount FROM Cars p JOIN CarCompany c ON p.carCompany = c where p.model = :model");
			query.setParameter("model", model);
			carlist = query.getResultList();
		} catch (PersistenceException p) {
			System.out.println(p);
		}
		return carlist;
	}

	public static List<Object[]> getCarByBandModelCapacity(String brand, String model, int seatCapacity) {
		List<Object[]> carlist = null;
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Query query = em.createQuery("SELECT p.carId,p.model,c.companyName, p.availability, p.mileage, p.year, "
					+ "p.Location, p.amount FROM Cars p JOIN CarCompany c ON p.carCompany = c where "
					+ "p.model = :model And p.seatingcapacity = :seating And c.companyName = :companyName");
			query.setParameter("model", model);
			query.setParameter("companyName", brand);
			query.setParameter("seating", seatCapacity);
			carlist = query.getResultList();
		} catch (PersistenceException p) {
			System.out.println(p);
		}
		return carlist;
	}

	public static List<Object[]> getCarByLocation(String location) {
		List<Object[]> carlist = null;
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Query query = em.createQuery("SELECT p.carId,p.model,c.companyName, p.availability, p.mileage, p.year, "
					+ "p.Location, p.amount FROM Cars p JOIN CarCompany c ON p.carCompany = c where "
					+ "p.Location = :location");
			query.setParameter("location", location);
			carlist = query.getResultList();
		} catch (PersistenceException p) {
			System.out.println(p);
		}
		return carlist;
	}

//	---------------------------------------------------------------------
	public static void searchByBrand(Scanner sc) {
		System.out.print("Enter the brand you wish to see : ");
		String brand = sc.next();
		List<Object[]> clist = getCarList(brand);
		if (clist.size() == 0) {
			System.out.println("Car not found please enter the valid brand (OR) company-Name.");
		}
		for (Object[] a : clist) {
			System.out.println("Car-ID : " + a[0] + ", Car-Model : " + a[1] + ", Car-Company : " + a[2]
					+ ", car-Availibity : " + a[3] + ", car-mileage : " + a[4] + ", car-Manufacturung Year : " + a[5]
					+ ", Available-Location : " + a[6] + ", Price-per-Day : " + a[7]);
		}
	}

	public static void searchByModel(Scanner sc) {
		System.out.print("Enter the car-model you wish to see : ");
		String model = sc.next();
		List<Object[]> clist = getCarByModel(model);
		if (clist.size() == 0) {
			System.out.println("Car not found please enter the valid Model.");
		}
		for (Object[] a : clist) {
			System.out.println("Car-ID : " + a[0] + ", Car-Model : " + a[1] + ", Car-Company : " + a[2]
					+ ", car-Availibity : " + a[3] + ", car-mileage : " + a[4] + ", car-Manufacturung Year : " + a[5]
					+ ", Available-Location : " + a[6] + ", Price-per-Day : " + a[7]);
		}
	}

	public static void searchByBandModelCapacity(Scanner sc) {
		System.out.print("Enter Car-Brand or company you wish to see : ");
		String brand = sc.next();
		System.out.print("Enter car-name or model you wish to see : ");
		String model = sc.next();
		System.out.print("Enter seating capacity : ");
		int seatCapacity = sc.nextInt();
		List<Object[]> clist = getCarByBandModelCapacity(brand, model, seatCapacity);
		if (clist.size() == 0) {
			System.out.println("Car not found please enter the valid Model.");
		}
		for (Object[] a : clist) {
			System.out.println("Car-ID : " + a[0] + ", Car-Model : " + a[1] + ", Car-Company : " + a[2]
					+ ", car-Availibity : " + a[3] + ", car-mileage : " + a[4] + ", car-Manufacturung Year : " + a[5]
					+ ", Available-Location : " + a[6] + ", Price-per-Day : " + a[7]);
		}
	}

	public static void SearchByLocation(Scanner sc) {
		System.out.print("Enter the Location to rent the car : ");
		String Location = sc.next();
		List<Object[]> clist = getCarByLocation(Location);
		if (clist.size() == 0) {
			System.out.println("Car not found on your location.");
		}
		for (Object[] a : clist) {
			System.out.println("Car-ID : " + a[0] + ", Car-Model : " + a[1] + ", Car-Company : " + a[2]
					+ ", car-Availibity : " + a[3] + ", car-mileage : " + a[4] + ", car-Manufacturung Year : " + a[5]
					+ ", Available-Location : " + a[6] + ", Price-per-Day : " + a[7]);
		}
	}

	public static void searchCar(Scanner sc) {
		int choice = 0;
		do {
			System.out.println("1. Search car by Brand");
			System.out.println("2. Search car by model");
			System.out.println("3. Search car Seating Capacity, model, brand");
			System.out.println("4. Search Based On location");
			System.out.println("0. To Main Menu");
			System.out.print("Enter your Selection : ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				searchByBrand(sc);
				break;
			case 2:
				searchByModel(sc);
				break;
			case 3:
				searchByBandModelCapacity(sc);
				break;
			case 4:
				SearchByLocation(sc);
				break;
			case 0:
				break;
			default:
				System.out.println("Wrong Choice");
			}
		} while (choice != 0);

	}

	public static void bookCar(Scanner sc) {
		System.out.print("Enter Car-id you wish to buy : ");
		int id = sc.nextInt();

		System.out.print("Enter booking Start-date : ");
		String startDate = sc.next();
		LocalDate date1 = LocalDate.parse(startDate);

		System.out.print("Enter booking end-date : ");
		String endDate = sc.next();
		LocalDate date2 = LocalDate.parse(endDate);

		System.out.print("Enter booking location : ");
		String location = sc.next();
//		int payment, String modeofPayment,Cars car,String location,LocalDate startDate, LocalDate EndDate

		ICarService ics = new CarServiceImpl();
//		IBooking booking = new BookingsImpl();
		IBookingService booking = new BookingServiceImp();
		try {
			Cars car = ics.getCar(id);
			booking.BookCar(0, "online", car, location, date1, date2, sc);
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			System.out.println("Unable to process your request.");
		}
	}

	public static void deleteBooking(Scanner sc) {
		System.out.print("Enter booking id you wish to cancel : ");
		int id = sc.nextInt();
		IBookingService booking = new BookingServiceImp();
		try {
			booking.deleteBookings(id);
			System.out.println("Booking got successfully amount will be credited with in 24hrs");
		} catch (NoRecordFoundException | SomethingWentWrongException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void viewAllTransactions() {
		IBooking b = new BookingsImpl();
		try {
			List<Object[]> clist = b.getCustomerBooking();
			for (Object[] a : clist) {
//				ct.car_model, c.location, b.id, b.status, t.totalAmount, t.transactiondata, t.modeofTransactoion
				System.out.println("Car_model : " + a[0] + ", Booked_Location : " + a[1] + ", Booking Id : " + a[2]
						+ ", Booking_status : " + a[3] + ", Total_Booking_amount : " + a[4] + ", Transaction_date : "
						+ a[5] + ", mode_of_Transactoion : " + a[6]);
			}
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void viewAllCars() {
		ICarService ics = new CarServiceImpl();
		try {
			List<Object[]> carlist = ics.getCarListCustomer();
			for (Object[] a : carlist) {
				System.out.println(
						"Car-ID : " + a[0] + ", Car-Model : " + a[1] + ", Car-Company : " + a[2] + ", car-Availibity : "
								+ a[3] + ", car-mileage : " + a[4] + ", car-Manufacturung Year : " + a[5]);
			}
			System.out.println();
		}catch (SomethingWentWrongException| NoRecordFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void userMenu(Scanner sc) {
		int choice = 0;
		do {
			DisplayCustomerMenu();
			System.out.print("Enter your selection : ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				viewAllCars();
				break;
			case 2:
				AdminUI.viewSingleCar(sc);
				break;
			case 3:
				searchCar(sc);
				break;
			case 4:
				bookCar(sc);
				break;
			case 5:
				deleteBooking(sc);
				break;
//			case 6:
////				modifyBooking(sc);
//				break;
			case 6:
				viewAllTransactions();
				break;
			case 7:
				deleteAccount(sc);
				break;
			case 0:
				System.out.println("Logged out successfull.");
			default:
				System.out.println("Invalid selection.");
			}
		} while (choice != 0);
	}
}
