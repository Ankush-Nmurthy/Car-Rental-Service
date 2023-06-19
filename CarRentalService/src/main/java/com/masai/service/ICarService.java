package com.masai.service;

import java.util.List;

import com.masai.entity.CarCompany;
import com.masai.entity.Cars;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface ICarService {
	void addCarAndCompanyBoth(CarCompany car) throws SomethingWentWrongException;
	void updateCar(Cars car) throws SomethingWentWrongException , NoRecordFoundException; 
	List<Object[]> getCarList() throws SomethingWentWrongException, NoRecordFoundException;
	Cars getCar(int id) throws SomethingWentWrongException, NoRecordFoundException;
	void addOnlyCar(Cars car) throws SomethingWentWrongException;
	public void addCompany(CarCompany cc) throws SomethingWentWrongException ;
	public CarCompany getCarCompany(String name) throws SomethingWentWrongException, NoRecordFoundException;
	public void deleteCar(int id) throws SomethingWentWrongException, NoRecordFoundException;
	public List<Object[]> getCarListCustomer() throws SomethingWentWrongException, NoRecordFoundException;
}
