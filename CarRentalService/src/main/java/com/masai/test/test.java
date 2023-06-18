package com.masai.test;

import java.util.Scanner;

import com.masai.dao.CarsServiceDaoImpl;
import com.masai.ui.AdminUI;
import com.masai.ui.CustomerUI;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class test {
	static EntityManagerFactory emf = null;
	static {
		emf = Persistence.createEntityManagerFactory("CarRentalService");
	}
//	static void cars() {
//		EntityManager em = null;
//		EntityTransaction et = null;
//		
//		try {
//			em= emf.createEntityManager();
//			Cars car = new Cars("suzuki", "Waganor", 2010, 18, true);
//			Cars car2 = new Cars("suzuki", "Waganor", 2010, 18, true);
//			
//			et = em.getTransaction();
//			et.begin();
//			em.persist(car);
//			em.persist(car2);
//			et.commit();
//		}catch(Exception e) {
//			et.rollback();
//			System.out.println(e.getMessage());
//		}finally{
//			em.close();
//		}
//	}
	public static void main(String[] args) {
////		cars();
//		Cars car = new Cars("toyota", "innove", 2015, 18, true);
		CarsServiceDaoImpl csr = new CarsServiceDaoImpl();
//		try {
//			csr.addCar(car);
//		} catch (SomethingWentWrongException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		AdminUI.SearchIfCarIsPresent("mahindra");
		
		Scanner sc = new Scanner(System.in);
		AdminUI ai = new AdminUI();
//		ai.addCompany(sc);
		
//		ai.addCar(sc);
//		ai.viewAllCars();
//		CarCompany cc = ai.getCarCompany("mahindra");
//		System.out.println(cc);
		
//		---------------
//		try {
//			Cars car= csr.getCar(2);
//			System.out.println(car);
//		} catch (SomethingWentWrongException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoRecordFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		---------------------
//		try {
//			csr.deleteCar(3);
//		} catch (SomethingWentWrongException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoRecordFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ai.viewAllCars();
		
		
//		----------------------
//		AdminUI.viewSingleCar(sc);
		
//		-------------------------
		
//		CustomerUI.searchCar(sc);
//		--------------------
//		System.out.println(CustomerUI.getCarList("mahindra"));
		
//		============================
//		CustomerUI.searchByBrand(sc);
//		===============================
		CustomerUI.SearchByLocation(sc);
		
	}
}
