package com.masai.dao;

import java.util.List;

import com.masai.entity.Customer;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public void addCustomer(Customer customer) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void login(String username, String password) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePassword(String oldPassword, String newPassword) throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount() throws SomethingWentWrongException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Object[]> getCustomerList() throws SomethingWentWrongException, NoRecordFoundException {
		// TODO Auto-generated method stub
		return null;
	}

}
