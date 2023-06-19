package com.masai.dao;

import java.util.List;

import com.masai.entity.CarCompany;
import com.masai.entity.Cars;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

import jakarta.persistence.Entity;
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
		}catch(IllegalArgumentException | PersistenceException p) {
			et.rollback();
			throw new SomethingWentWrongException("Unable to process your request, try againg later..");
		}
		finally {
			em.close();
		}
	}

	
	@Override
	public void updateCar(Cars car) throws SomethingWentWrongException, NoRecordFoundException {
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Cars carfromDb = em.find(Cars.class, car.getCarId());
			// check if the car exist in data base;
			if(carfromDb == null) {
				throw new NoRecordFoundException("No car found with the given Id");
			}
			
			// --> we are here means Car with the given id Exist
			EntityTransaction et = em.getTransaction();
			et.begin();
			carfromDb.setAvailability(car.isAvailability());
			carfromDb.setMileage(car.getMileage());
			carfromDb.setModel(car.getModel());
			carfromDb.setYear(car.getYear());
			et.commit();
		}
		catch(PersistenceException p) {
			p.printStackTrace();
			throw new SomethingWentWrongException("Unable to process request...");
		}
		finally {
			em.close();
		}
	}

	@Override
	public List<Object[]> getCarList() throws SomethingWentWrongException, NoRecordFoundException {
		EntityManager em = null;
		List<Object[]> allCars = null;
		try {
			em = EMUtilities.getEntityManager();
//			Query query = em.createQuery("SELECT p.carID,  c.companyName, p.availability, p.mileage, p.manufacturing_year FROM Cars p JOIN CarCompany c ON p.company = c");
			Query query = em.createQuery ("SELECT p.carId,p.model,c.companyName, p.availability, p.mileage, p.year FROM Cars p JOIN CarCompany c ON p.carCompany = c");
			allCars = (List<Object[]>)query.getResultList();
			if(allCars.size() == 0) {
				throw new NoRecordFoundException("No plan Found"); 
			}
		}catch(IllegalArgumentException ex) {
			ex.printStackTrace();
			throw new NoRecordFoundException("unable to process the request..");
		}
		finally {
			em.close();
		}
		return allCars;
	}

	@Override
	public List<Object[]> getCarListCustomer() throws SomethingWentWrongException, NoRecordFoundException {
		EntityManager em = null;
		List<Object[]> allCars = null;
		try {
			em = EMUtilities.getEntityManager();
//			Query query = em.createQuery("SELECT p.carID,  c.companyName, p.availability, p.mileage, p.manufacturing_year FROM Cars p JOIN CarCompany c ON p.company = c");
			Query query = em.createQuery ("SELECT p.carId,p.model,c.companyName, p.availability, p.mileage, p.year FROM Cars p JOIN CarCompany c ON p.carCompany = c where p.availability = true");
			allCars = (List<Object[]>)query.getResultList();
			if(allCars.size() == 0) {
				throw new NoRecordFoundException("No plan Found"); 
			}
		}catch(IllegalArgumentException ex) {
			ex.printStackTrace();
			throw new NoRecordFoundException("unable to process the request..");
		}
		finally {
			em.close();
		}
		return allCars;
	}
	@Override
	public Cars getCar(int id) throws SomethingWentWrongException, NoRecordFoundException {
		Cars car = null;
		try(EntityManager em = EMUtilities.getEntityManager()){
			car = em.find(Cars.class, id);
			if(car == null) {
				throw new NoRecordFoundException("No Car is available at given id");
			}
		}catch(IllegalArgumentException ex) {
			throw new SomethingWentWrongException("Unable to fetch car details, please try again later");
		}
		return car;
	}

	@Override
	public void addOnlyCar(Cars car) throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			EntityTransaction et = em.getTransaction();
			et = em.getTransaction();
			et.begin();
			em.persist(car);
			et.commit();
//			System.out.println("Car added successfully...");
		}catch(IllegalArgumentException | PersistenceException p) {
			p.getStackTrace();
//			throw new SomethingWentWrongException("Unable to add car...");
			System.out.println(p.getStackTrace());
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
			Query query = em.createQuery("SELECT count(c) FROM CarCompany c WHERE companyName = :companyName");
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
	
	@Override
	public CarCompany getCarCompany(String name) throws SomethingWentWrongException, NoRecordFoundException{
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


	@Override
	public void deleteCar(int id) throws SomethingWentWrongException, NoRecordFoundException {
		EntityManager em = null;
		Cars car = getCar(id);  // car came here..
		// car gone to detached
		System.out.println(car);
		EntityTransaction et = null;
		try {
			em = EMUtilities.getEntityManager();
			et = em.getTransaction();
			et.begin();
			CarCompany cc = em.find(CarCompany.class, car.getCarCompany().getId());
			Cars c = em.find(Cars.class, id); //Retrieving the car from the detached state to merge state.
			cc.getCars().remove(c); //setting the car the car value in the car-company by deleting the car 
			em.merge(cc);
			em.remove(c);
			et.commit();
			System.out.println("Car deleted successfully...");
		}catch(IllegalArgumentException | PersistenceException p) {
			et.rollback();
			p.printStackTrace();
			throw new SomethingWentWrongException("unable to processs your request....");
		}
		finally {
			em.close();
		}
	}

}
