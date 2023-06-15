package com.masai.service;

import java.util.List;

import com.masai.entity.CarCompany;
import com.masai.entity.Cars;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface ICarService {
	void addCar(CarCompany car) throws SomethingWentWrongException;
	void updateCar() throws SomethingWentWrongException , NoRecordFoundException; 
	List<Cars> getCarList() throws SomethingWentWrongException, NoRecordFoundException;
	Cars getCar() throws SomethingWentWrongException, NoRecordFoundException;
}
