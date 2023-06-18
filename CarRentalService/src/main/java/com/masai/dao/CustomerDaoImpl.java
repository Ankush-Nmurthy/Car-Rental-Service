package com.masai.dao;

import java.util.List;
import com.masai.entity.Customer;
import com.masai.entity.LoggedInUserId;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void addCustomer(Customer customer) throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Query query = em.createQuery("select count(c) FROM Customer c WHERE username = :username");
			query.setParameter("username", customer.getUsername());
			
			if((Long)query.getSingleResult() > 0) {
				throw new SomethingWentWrongException("The username" + customer.getUsername() + " is already occupied");
			}
			
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(customer);
			et.commit();
		}
		catch(PersistenceException p) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		}
		finally {
			em.close();
		}
	}

	@Override
	public void login(String username, String password) throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			
			Query query = em.createQuery("Select c.id from Customer c where username = :username And password = :password And isDeleted = 0");
			query.setParameter("username", username);
			query.setParameter("password", password);
			List<Integer> clist = (List<Integer>)query.getResultList();
			if(clist.size() == 0) {
				throw new SomethingWentWrongException("Incorrect username and password..");
			}
			LoggedInUserId.loggedInUserId = clist.get(0);
		}
		catch(PersistenceException p) {
			throw new SomethingWentWrongException("Unable to process request, try again later");
		}
		finally {
			em.close();
		}
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Query query = em.createQuery("select count(c) from Customer c where password = :oldpassword And id = :id");
			query.setParameter("oldpassword", oldPassword);
			query.setParameter("id", LoggedInUserId.loggedInUserId);
			Long userCount = (Long) query.getSingleResult();
			if(userCount == 0) {
				throw new SomethingWentWrongException("Wrong old password entered please check again.");
			}
			
			Customer customer = em.find(Customer.class, LoggedInUserId.loggedInUserId);
			EntityTransaction et = em.getTransaction();
			et.begin();
			customer.setPassword(newPassword);
			et.commit();
		}
		catch(PersistenceException p) {
			throw new SomethingWentWrongException("unable to process your request at this time..");
		}
		finally {
			em.close();
		}
	}

	@Override
	public void deleteAccount() throws SomethingWentWrongException {
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Customer customer = em.find(Customer.class, LoggedInUserId.loggedInUserId);
			EntityTransaction et = em.getTransaction();
			et.begin();
			customer.setIsDeleted(1);
			et.commit();
		} catch (PersistenceException e) {
			throw new SomethingWentWrongException("Unable to process your request at this time.");
		}
		finally {
			em.close();
		}
	}

	@Override
	public List<Object[]> getCustomerList() throws SomethingWentWrongException, NoRecordFoundException {
		EntityManager em = null;
		List<Object[]> customerList = null;
		try {
			em = EMUtilities.getEntityManager(); 
			Query query = em.createQuery("Select c.id, c.name, c.username, c.Age, c.location , c.isDeleted From Customer c");
			customerList = query.getResultList();
			if(customerList.size() == 0) {
				throw new SomethingWentWrongException("No Customer records found in database.");
			}
		}
		catch(PersistenceException p) {
			throw new SomethingWentWrongException("Unable to process your request, try again later.");
		}
		finally {
			em.close();
		}
		return customerList;
	}

}
