package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.entity.Cars;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface IBookingService {
	public void BookCar(int payment, String modeofPayment,Cars car,String location,LocalDate startDate, LocalDate EndDate,Scanner sc) throws SomethingWentWrongException, NoRecordFoundException;
	public List<Object[]> getCustomerBooking() throws SomethingWentWrongException, NoRecordFoundException;
	public void deleteBookings(int id)throws NoRecordFoundException, SomethingWentWrongException;
}
