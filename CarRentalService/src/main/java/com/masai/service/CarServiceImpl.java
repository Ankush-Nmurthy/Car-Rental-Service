package com.masai.service;

import java.util.List;

import com.masai.dao.CarsServiceDaoImpl;
import com.masai.dao.ICarsServiceDAO;
import com.masai.entity.CarCompany;
import com.masai.entity.Cars;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class CarServiceImpl implements ICarService {

	@Override
	public void addCar(CarCompany car) throws SomethingWentWrongException {
		ICarsServiceDAO carService = new CarsServiceDaoImpl();
		carService.addCar(car);
		System.out.println(car);
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

}
