package com.masai.ui;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import com.masai.dao.CarsServiceDaoImpl;
import com.masai.dao.EMUtilities;
import com.masai.dao.ICarsServiceDAO;
import com.masai.entity.CarCompany;
import com.masai.entity.Cars;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;
import com.masai.service.CarServiceImpl;
import com.masai.service.ICarService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

// Generate reports and analytics regarding car 
//rental statistics, revenue, and inventory management.
public final class AdminUI {

	public static void viewSingleCar(Scanner sc) {
		System.out.print("Enter ID of the car tobe viewed : ");
		int id = sc.nextInt();
		try (EntityManager em = EMUtilities.getEntityManager()) {
			Cars car = em.find(Cars.class, id);
			if (car == null) {
				System.out.println("No cars Exists with the given ID.");
			} else {
				System.out.println(car);
			}
		} catch (IllegalArgumentException | PersistenceException p) {
			System.out.println(p.getMessage());
		}
	}

	public static CarCompany getCarCompany(String name) {
		List<CarCompany> a = null;
		try (EntityManager em = EMUtilities.getEntityManager()) {
			String selectQuery = "SELECT c FROM CarCompany c WHERE c.companyName = :na";
			Query query = em.createQuery(selectQuery);
			query.setParameter("na", name);
			a = (List<CarCompany>) query.getResultList();
			if (!a.isEmpty()) {
				return a.get(0);
			}
		} catch (IllegalArgumentException | PersistenceException p) {
			System.out.println(p.getMessage());
		}
		return null;
	}

	public void addCompany(Scanner sc) {
		System.out.print("Enter Brand or Company name of Car : ");
		String brand = sc.next();
		CarCompany cc = new CarCompany(brand, null);
		ICarsServiceDAO ics = new CarsServiceDaoImpl();
		try {
			ics.addCompany(cc);
		} catch (SomethingWentWrongException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void addCar(Scanner sc) {
		System.out.print("Enter Brand or Company name of Car : ");
		String brand = sc.next();
		System.out.print("Enter Model of Car : ");
		String model = sc.next();
		System.out.print("Enter Year of Manufacture : ");
		int year = sc.nextInt();
		System.out.print("Enter Mileage of the Car : ");
		int mileage = sc.nextInt();
		System.out.print("Enter the rent per day : ");
		double amount = sc.nextDouble();
		System.out.print("Enter the avialbility of car (true / false) : ");
		boolean availability = sc.nextBoolean();
		System.out.print("Enter the location were the car is available : ");
		String location = sc.next();
		System.out.print("Enter the seating capacity of car : ");
		int seatingCapacity = sc.nextInt();
//		if Carcompany present in data base add only car-->
		if (getCarCompany(brand) != null) {
			try {
				ICarService ics = new CarServiceImpl();
//				ICarsServiceDAO ics = new CarsServiceDaoImpl();
				CarCompany cc = getCarCompany(brand);
				Cars car = new Cars(model, year, mileage, availability, amount, location, cc);
				car.setSeatingcapacity(seatingCapacity); // added seating capacity explicitly.
				ics.addOnlyCar(car);
				System.out.println("Car added successfully...");
			} catch (SomethingWentWrongException p) {
				System.out.println("unable to process your request.");
			}
			System.out.println();
		} else { // if not present in database add car and company both
			try {
//				ICarsServiceDAO ics = new CarsServiceDaoImpl();
				ICarService ics = new CarServiceImpl();
				ics.addCompany(new CarCompany(brand, new HashSet<>()));
				CarCompany cc = getCarCompany(brand);
				Cars car = new Cars(model, year, mileage, availability, amount, location, cc);
				car.setSeatingcapacity(seatingCapacity); // added seating capacity explicitly.
				ics.addOnlyCar(car);
				System.out.println("Car added successfully...");
			} catch (SomethingWentWrongException p) {
				System.out.println("unable to process your request.");
			}
			System.out.println();
		}
	}

	public static void updateCar(Scanner sc) {
		System.out.print("Enter ID of the car");
		int id = sc.nextInt();
		System.out.print("Enter Brand or Company name of Car : ");
		String brand = sc.next();
		System.out.print("Enter Model of Car : ");
		String model = sc.next();
		System.out.print("Enter Year of Manufacture : ");
		int year = sc.nextInt();
		System.out.print("Enter Mileage of the Car : ");
		int mileage = sc.nextInt();
		System.out.print("Enter the rent per day : ");
		double amount = sc.nextDouble();
		System.out.print("Enter the avialbility of car (true / false) : ");
		boolean availability = sc.nextBoolean();
		System.out.print("Enter the location were the car is available : ");
		String location = sc.next();
		System.out.print("Enter the seating capacity of car : ");
		int seatingCapacity = sc.nextInt();

		try {
			CarCompany cc = getCarCompany(brand);
			Cars car = new Cars(model, year, mileage, availability, amount, location, null);
			car.setSeatingcapacity(seatingCapacity); // added seating capacity explicitly.
			car.setCarId(id);
			ICarService ics = new CarServiceImpl();
			ics.updateCar(car);
			System.out.println("Car updated Successfully...");
		} catch (SomethingWentWrongException | NoRecordFoundException n) {
			System.out.println(n.getMessage());
		}
		System.out.println();
	}

	public static void viewAllCars() {
		ICarService ic = new CarServiceImpl();
		try {
			List<Object[]> carlist = ic.getCarList();
			for (Object[] a : carlist) {
				System.out.println(
						"Car-ID : " + a[0] + ", Car-Model : " + a[1] + ", Car-Company : " + a[2] + ", car-Availibity : "
								+ a[3] + ", car-mileage : " + a[4] + ", car-Manufacturung Year : " + a[5]);
			}
			System.out.println();
		} catch (SomethingWentWrongException | NoRecordFoundException n) {
			System.out.println(n.getMessage());
		}
	}

	public static void deleteCar(Scanner sc) {
		System.out.print("Enter the car id you wish to delete : ");
		int id = sc.nextInt();
		ICarService ic = new CarServiceImpl();
		try {
			ic.deleteCar(id);
			System.out.println("Car deleted Successfully....");
		} catch (SomethingWentWrongException | NoRecordFoundException e) {
			e.printStackTrace();
			System.out.println("Unable to process your request..");
		}
	}

	public static void generateReport(Scanner sc) {
		int choice = 0;
		do {
			System.out.println("1. Rental status");
			System.out.println("2. Revenue generated by car-model name");
			System.out.println("3. Cars in inventory");
			System.out.print("Enter your selection : ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				rentalStatus();
				break;
			case 2:
				revenueGenerated();
				break;
			case 3:
				carsInInventory();
				break;

			}
		} while (choice != 0);

	}

//	----------------- Admin report.---------------

	public static void revenueGenerated() {
		EntityManager em = null;
		List<Object[]> list = null;
		try {
			em = EMUtilities.getEntityManager();
			em = EMUtilities.getEntityManager();
//			Query query = em.createQuery("SELECT p.carID,  c.companyName, p.availability, p.mileage, p.manufacturing_year FROM Cars p JOIN CarCompany c ON p.company = c");
			Query query = em.createNativeQuery(
					"select ct.carId, ct.car_model, sum(t.totalAmount) from bookings b join transaction t on b.id = t.booking_id join cars_table ct on ct.carId = b.car_id group by ct.carId");
			list = (List<Object[]>) query.getResultList();
			for (Object[] a : list) {
				System.out.println("Car_id: " + a[0] + ", CarModel: " + a[1] + ", totalAmountGenerated: " + a[2]);
			}
		} catch (PersistenceException p) {
			System.out.println("No cars in inventory.");
		}
	}

	public static void rentalStatus() {
		EntityManager em = null;
		List<Object[]> list = null;
		try {
			em = EMUtilities.getEntityManager();
			em = EMUtilities.getEntityManager();
//			Query query = em.createQuery("SELECT p.carID,  c.companyName, p.availability, p.mileage, p.manufacturing_year FROM Cars p JOIN CarCompany c ON p.company = c");
			Query query = em.createNativeQuery(
					"select ct.car_model, c.name, b.status from bookings b join transaction t on b.id = t.booking_id join cars_table ct on ct.carId = b.car_id join customer c on b.customer_id = c.id;");
			list = (List<Object[]>) query.getResultList();
			for (Object[] a : list) {
				System.out.println("Car_model: " + a[0] + ", Customer-Name: " + a[1] + ", RentalStatus: " + a[2]);
			}
		} catch (PersistenceException p) {
			System.out.println("No cars in inventory.");
		}
	}
	
	public static void carsInInventory(){
		EntityManager em = null;
		List<Object[]> list = null;
		try {
			em = EMUtilities.getEntityManager();
			em = EMUtilities.getEntityManager();
//			Query query = em.createQuery("SELECT p.carID,  c.companyName, p.availability, p.mileage, p.manufacturing_year FROM Cars p JOIN CarCompany c ON p.company = c");
			Query query = em.createNativeQuery(" select ct.companyName, c.car_model, c.location, c.amount from cars_table c join carcompany ct on c.car_company_id = ct.id;");
			list = (List<Object[]>) query.getResultList();
			for (Object[] a : list) {
				System.out.println("CarCompany: " + a[0] + ", Car-Model: " + a[1] + ", Carlocation: " + a[2]+ ", Rent-Price: "+a[3]);
			}
		} catch (PersistenceException p) {
			System.out.println("No cars in inventory.");
		}
	}
}
