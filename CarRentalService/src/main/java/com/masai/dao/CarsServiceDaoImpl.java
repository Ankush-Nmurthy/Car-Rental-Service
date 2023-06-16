package com.masai.dao;

import java.util.List;

import com.masai.entity.CarCompany;
import com.masai.entity.Cars;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CarsServiceDaoImpl implements ICarsServiceDAO{

	@Override
	public void addCarAndCompanyBoth(CarCompany car) throws SomethingWentWrongException {
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
			em.close();
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

	@Override
	public void addOnlyCar(Cars car) throws SomethingWentWrongException {
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
			p.getStackTrace();
			throw new SomethingWentWrongException("Unable to add car...");
		}
		finally {
			em.close();
		}
	}

	@Override
	public void addCompany(CarCompany cc) throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Query query = em.createQuery("SELECT count(c) FROM Company c WHERE companyName = :companyName");
			query.setParameter("companyName", cc.getCompanyName());
			if((Long)query.getSingleResult() > 0) {
				//you are here means company with given name exists so throw exceptions
				throw new SomethingWentWrongException("Company already exists with name " + cc.getCompanyName());
			}
			
			//you are here means no company with given name
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(cc);
			et.commit();
		}catch(PersistenceException ex) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		}finally{
			em.close();
		}
	}

}
