package com.masai.service;

import java.util.List;

import com.masai.dao.CustomerDao;
import com.masai.dao.CustomerDaoImpl;
import com.masai.entity.Customer;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class CustomerServiceImpl implements ICustomerService{

	@Override
	public void addCustomer(Customer customer) throws SomethingWentWrongException {
		CustomerDao customerDao = new CustomerDaoImpl();
		customerDao.addCustomer(customer);
	}

	@Override
	public void login(String username, String password) throws SomethingWentWrongException , NoRecordFoundException{
		CustomerDao customerDao = new CustomerDaoImpl();
		customerDao.login(username, password);
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) throws SomethingWentWrongException {
		CustomerDao customerDao = new CustomerDaoImpl();
		customerDao.changePassword(oldPassword, newPassword);
	}

	@Override
	public void deleteAccount() throws SomethingWentWrongException {
		CustomerDao customerDao = new CustomerDaoImpl();
		customerDao.deleteAccount();
	}

	@Override
	public List<Object[]> getCustomerList() throws SomethingWentWrongException, NoRecordFoundException {
		CustomerDao customerDao = new CustomerDaoImpl();
		return customerDao.getCustomerList();
	}

}
