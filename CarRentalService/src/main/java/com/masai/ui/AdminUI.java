package com.masai.ui;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import com.masai.dao.EMUtilities;
import com.masai.entity.CarCompany;
import com.masai.entity.Cars;
import com.masai.exception.SomethingWentWrongException;
import com.masai.service.CarServiceImpl;
import com.masai.service.ICarService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public final class AdminUI {
	
	public static void getCarCompany(String name) {
		try(EntityManager em = EMUtilities.getEntityManager()){
			String selectQuery = "Select c from CarCompany c where c.companyName is ";
			Query query = em.createQuery(selectQuery);
			query.setParameter("na", name);
			List<CarCompany> cc = (List<CarCompany>)query.getResultList();
			System.out.println(cc);
//			return cc;
		}
		catch(IllegalArgumentException | PersistenceException p) {
			System.out.println(p.getMessage());
		}
//		return null;
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
		System.out.print("Enter the avialbility of car (true / false) : ");
		boolean availability = sc.nextBoolean();

		CarCompany cc = new CarCompany(brand, new HashSet<>());
		
		Cars car = new Cars(model, year, mileage, availability, null);
		car.setCarCompany(cc);
		cc.getCars().add(car);
		
		ICarService carservice = new CarServiceImpl();
		try {
			carservice.addCar(cc);
			System.out.println("car added successfully..");
		} catch (SomethingWentWrongException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void updateCar(Scanner sc) {
		
	}
}
