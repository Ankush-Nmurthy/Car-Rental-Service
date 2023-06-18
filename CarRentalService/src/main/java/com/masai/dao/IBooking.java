package com.masai.dao;

import java.time.LocalDate;
import java.util.List;

import com.masai.entity.Cars;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface IBooking {
	public void BookCar(int payment, String modeofPayment,Cars car,String location,LocalDate startDate, LocalDate EndDate) throws SomethingWentWrongException, NoRecordFoundException;
	public List<Object[]> getCustomerBooking() throws SomethingWentWrongException, NoRecordFoundException;
}
