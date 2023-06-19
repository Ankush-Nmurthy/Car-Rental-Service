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
	public void addCarAndCompanyBoth(CarCompany car) throws SomethingWentWrongException {
		ICarsServiceDAO carService = new CarsServiceDaoImpl();
		carService.addCarAndCompanyBoth(car);
	}

	@Override
	public void updateCar(Cars car) throws SomethingWentWrongException, NoRecordFoundException {
		ICarsServiceDAO carService = new CarsServiceDaoImpl();
		carService.updateCar(car);
	}

	@Override
	public List<Object[]> getCarList() throws SomethingWentWrongException, NoRecordFoundException {
		ICarsServiceDAO carService = new CarsServiceDaoImpl();
		List<Object[]> carlist = carService.getCarList();
		return carlist;
	}

	@Override
	public Cars getCar(int id) throws SomethingWentWrongException, NoRecordFoundException {
		ICarsServiceDAO carService = new CarsServiceDaoImpl();
		return carService.getCar(id);
	}

	@Override
	public void addOnlyCar(Cars car) throws SomethingWentWrongException {
		ICarsServiceDAO carService = new CarsServiceDaoImpl();
		carService.addOnlyCar(car);
	}

	@Override
	public void addCompany(CarCompany cc) throws SomethingWentWrongException {
		ICarsServiceDAO carService = new CarsServiceDaoImpl();
		carService.addCompany(cc);
	}

	@Override
	public CarCompany getCarCompany(String name) throws SomethingWentWrongException, NoRecordFoundException {
		ICarsServiceDAO carService = new CarsServiceDaoImpl();
		return carService.getCarCompany(name);
	}

	@Override
	public void deleteCar(int id) throws SomethingWentWrongException, NoRecordFoundException {
		ICarsServiceDAO carService = new CarsServiceDaoImpl();
		carService.deleteCar(id);
	}

	@Override
	public List<Object[]> getCarListCustomer() throws SomethingWentWrongException, NoRecordFoundException {
		ICarsServiceDAO carService = new CarsServiceDaoImpl();
		return carService.getCarListCustomer();
	}
	
}
