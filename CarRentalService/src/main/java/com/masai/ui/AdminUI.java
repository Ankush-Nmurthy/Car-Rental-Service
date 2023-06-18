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
				Cars car = new Cars(model, year, mileage, availability, amount, location,cc);
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
				Cars car = new Cars(model, year, mileage, availability, amount,location ,cc);
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
			Cars car = new Cars(model, year, mileage, availability, amount,location,null);
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
}
