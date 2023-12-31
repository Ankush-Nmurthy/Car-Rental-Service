package com.masai.dao;

import java.util.List;

import com.masai.entity.Customer;
import com.masai.exception.NoRecordFoundException;
import com.masai.exception.SomethingWentWrongException;

public interface CustomerDao {
	void addCustomer(Customer customer) throws SomethingWentWrongException;
	void login(String username, String password) throws SomethingWentWrongException;
	void changePassword(String oldPassword, String newPassword) throws SomethingWentWrongException;
	void deleteAccount() throws SomethingWentWrongException;
	List<Object[]> getCustomerList() throws SomethingWentWrongException, NoRecordFoundException;
}
