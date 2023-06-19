package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.dao.BookingsImpl;
import com.masai.dao.IBooking;
import com.masai.entity.Cars;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class BookingServiceImp implements IBookingService{

	@Override
	public void BookCar(int payment, String modeofPayment, Cars car, String location, LocalDate startDate,
			LocalDate EndDate, Scanner sc) throws SomethingWentWrongException, NoRecordFoundException {
		IBooking booking = new BookingsImpl();
		booking.BookCar(payment, modeofPayment, car, location, startDate, EndDate, sc);
	}

	@Override
	public List<Object[]> getCustomerBooking() throws SomethingWentWrongException, NoRecordFoundException {
		IBooking booking = new BookingsImpl();
		return booking.getCustomerBooking();
	}

	@Override
	public void deleteBookings(int id) throws NoRecordFoundException, SomethingWentWrongException {
		IBooking booking = new BookingsImpl();
		booking.deleteBookings(id);
	}

}
