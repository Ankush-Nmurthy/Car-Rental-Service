package com.masai.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;
import com.masai.entity.Bookings;
import com.masai.entity.Cars;
import com.masai.entity.Customer;
import com.masai.entity.LoggedInUserId;
import com.masai.entity.Transaction;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class BookingsImpl implements IBooking{
	public void BookCar(int payment, String modeofPayment,Cars car,String location,LocalDate startDate, LocalDate EndDate, Scanner sc) throws SomethingWentWrongException, NoRecordFoundException{
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Customer customer = em.find(Customer.class, LoggedInUserId.loggedInUserId);
			System.out.println(LoggedInUserId.loggedInUserId);
			Bookings booking = new Bookings(customer, car, location, startDate, EndDate, null);
			long daysDifference = ChronoUnit.DAYS.between(startDate, EndDate);
			if(daysDifference < 1) {
				daysDifference = 1;
			}
			booking.setStatus("Booked");
			int amount  = (int) (car.getAmount() * daysDifference);
			System.out.print("Enter the mode of payment [online (or) offline] : ");
			String status = sc.next();
			System.out.println("The amount you should pay to complete booking : "+ amount);
			System.out.print("Enter amount here : ");
			int enteredAmount = sc.nextInt();
			if(enteredAmount != amount) {
				throw new SomethingWentWrongException("Wrong amount entered, the amount you are supposed to pay is : "+ amount);
			}
			Transaction transaction = new Transaction(LocalDateTime.now(),amount, booking);
			transaction.setModeofTransactoion(status);
			EntityTransaction et = em.getTransaction();
			et.begin();
			car.setAvailability(false);
			em.merge(car);
			em.persist(booking);
			em.persist(transaction);
			et.commit();
			System.out.println("Car got boooked successfully.");
		}catch(IllegalArgumentException | PersistenceException ex) {
			ex.printStackTrace();
			throw new SomethingWentWrongException("Unable to process the request, try after sometimes.");
		}
		finally {
			em.close();
		}
	}
	
	public List<Object[]> getCustomerBooking() throws SomethingWentWrongException, NoRecordFoundException{
		/*
		 * 	Query query = em.createNativeQuery("select ct.car_model, c.location, b.id as booking_id, b.status, t.totalAmount, t.transactiondata, t.modeofTransactoion from bookings b join transaction t on b.id = t.booking_id join cars_table ct on ct.carId = b.car_id join customer c on b.customer_id = c.id where c.id = :customerid");
			query.setParameter("customerid", customer.getId());
			clist = (List<Object[]>) query.getResultList();
		 * */
		List<Object[]> clist = null;
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Customer customer = em.find(Customer.class, LoggedInUserId.loggedInUserId);
			Query query = em.createQuery("select ct.model, c.location, b.id , b.status, t.totalAmount, t.transactiondata, t.modeofTransactoion from "
					+ "Bookings b join Transaction t on b.id = t.bookings.id join Cars ct on ct.carId = b.car.id join Customer c on b.customer.id = c.id where c.id = :customerid");
			query.setParameter("customerid", LoggedInUserId.loggedInUserId);
			clist = (List<Object[]>)query.getResultList();
			if(clist.size() == 0) {
				throw new SomethingWentWrongException("No records found. ");
			}
		}catch(PersistenceException p) {
			p.getStackTrace();
			throw new SomethingWentWrongException("Unable to process, try again later");
		}
		finally {
			em.close();
		}
		return clist;
	}
	
	@Override
	public void deleteBookings(int id)throws NoRecordFoundException, SomethingWentWrongException{
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			EntityTransaction et= em.getTransaction();
		    et.begin();
		    Bookings bookings = em.find(Bookings.class,id);
		    if(bookings == null) {
		    	throw new NoRecordFoundException("No record found on given booking ID");
		    }
		    Cars car = em.find(Cars.class, bookings.getCar().getCarId());
		    car.setAvailability(true);
		    em.merge(car);
		    bookings.setStatus("Cancelled");
		    em.merge(bookings);
		    et.commit();
			
		}catch(PersistenceException p) {
			p.getStackTrace();
			throw new SomethingWentWrongException("Unable to process your request try after sometime.");
		}
		finally {
			em.close();
		}
	}
}
