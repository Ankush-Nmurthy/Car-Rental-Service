package com.masai.test;

import com.masai.entity.CarCompany;
import com.masai.ui.AdminUI;

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
//		CarsServiceDaoImpl csr = new CarsServiceDaoImpl();
//		try {
//			csr.addCar(car);
//		} catch (SomethingWentWrongException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		AdminUI.SearchIfCarIsPresent("mahindra");
	}
}
