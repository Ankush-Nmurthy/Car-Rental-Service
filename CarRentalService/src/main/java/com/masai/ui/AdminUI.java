package com.masai.ui;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import com.masai.dao.EMUtilities;
import com.masai.entity.CarCompany;
import com.masai.entity.Cars;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public final class AdminUI {

	public static List<CarCompany> SearchIfCarIsPresent(String name) {
		try (EntityManager em = EMUtilities.getEntityManager()) {
			String selectQuery = "SELECT c FROM CarCompany c WHERE c.companyName = :na";
			Query query = em.createQuery(selectQuery);
			query.setParameter("na", name);
			List<CarCompany> a = (List<CarCompany>) query.getResultList();
			if (!a.isEmpty()) {
				return a;
			}
		} catch (IllegalArgumentException | PersistenceException p) {
			System.out.println(p.getMessage());
		}
		return null;
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

		CarCompany cc = new CarCompany(brand, null);
		Cars car = new Cars(model, year, mileage, availability, null);
		

	}

	public static void updateCar(Scanner sc) {

	}
}
