package com.masai.dao;

import java.util.List;

import com.masai.entity.CarCompany;
import com.masai.entity.Cars;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

public class CarsServiceDaoImpl implements ICarsServiceDAO{

	@Override
	public void addCar(CarCompany car) throws SomethingWentWrongException {
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			em = EMUtilities.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(car);
			et.commit();
			System.out.println("Car added successfully...");
		}catch(IllegalArgumentException | PersistenceException p) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to process your request, try againg later..");
		}
		finally {
//			em.close();
		}
		
	}

	@Override
	public void updateCar() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cars> getCarList() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cars getCar() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
