package com.masai.service;

import java.util.List;

import com.masai.entity.Customer;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class CustomerServiceImpl implements ICustomerService{

	@Override
	public void addCustomer(Customer customer) throws SomethingWentWrongException {
		
	}

	@Override
	public void login(String username, String password) throws SomethingWentWrongException {

	}

	@Override
	public void changePassword(String oldPassword, String newPassword) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount() throws SomethingWentWrongException {
	
	}

	@Override
	public List<Object[]> getCustomerList() throws SomethingWentWrongException, NoRecordFoundException {
		return null;
	}

}
