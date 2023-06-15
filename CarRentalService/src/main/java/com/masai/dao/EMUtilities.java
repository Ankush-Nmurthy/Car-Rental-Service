package com.masai.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMUtilities {
	public static EntityManagerFactory emf = null;
	static {
		emf = Persistence.createEntityManagerFactory("CarRentalService");
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
