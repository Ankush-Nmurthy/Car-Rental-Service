package com.masai.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "customer_id", nullable = false)
//	private Customer customer;
//
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "Car_id", nullable = false)
//	private Cars car;
//
//	@Column(name = "location" , nullable = false)
//	private String location;
//
//	@Column(name = "Start_date", nullable = false)
//	private LocalDate startDate;
//
//	@Column(name = "End_date", nullable = false)
//	private LocalDate endDate;
//
//	@Column(nullable = false)
//	private String status;
	@Override
	public void BookCar(int payment, String modeofPayment,Cars car,String location,LocalDate startDate, LocalDate EndDate) throws SomethingWentWrongException, NoRecordFoundException{
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Customer customer = em.find(Customer.class, LoggedInUserId.loggedInUserId);
			System.out.println(LoggedInUserId.loggedInUserId);
			Bookings booking = new Bookings(customer, car, location, startDate, EndDate, null);
			booking.setStatus("Booked");
			long daysDifference = ChronoUnit.DAYS.between(startDate, EndDate);
			if(daysDifference < 1) {
				daysDifference = 1;
			}
			int amount  = (int) (car.getAmount() * daysDifference);
			Transaction transaction = new Transaction(LocalDateTime.now(),amount, booking);
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.persist(booking);
			em.persist(transaction);
			et.commit();
		}catch(IllegalArgumentException | PersistenceException ex) {
			ex.printStackTrace();
			throw new SomethingWentWrongException("Unable to process the request, try after sometimes.");
		}
		finally {
			em.close();
		}
	}
	
	public List<Object[]> getCustomerBooking() throws SomethingWentWrongException, NoRecordFoundException{
		List<Object[]> list = null;
		EntityManager em = null;
		try {
			em = EMUtilities.getEntityManager();
			Customer customer = em.find(Customer.class, LoggedInUserId.loggedInUserId);
			Query query = em.createQuery("select c from Bookings b JOIN ");
			
		}catch(PersistenceException p) {
			p.getStackTrace();
			throw new SomethingWentWrongException("Unable to process, try again later");
		}
		finally {
			em.close();
		}
		return null;
	}
}
