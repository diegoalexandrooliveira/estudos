package com.acme.customers.api.rest.v1.services;

import java.sql.SQLException;
import java.util.List;

import com.acme.customers.lib.v1.Customer;

public interface CustomerService {
	
	Customer findCustomerById(String id) throws SQLException;
	
	List<Customer> findAllCustomers(Integer limit, Integer offset) throws SQLException;
	
	Customer createCustomer(Customer customer) throws SQLException;
	
	Customer updateCustomer(String id, Customer customer) throws SQLException;
	
	void deleteCustomerById(String id) throws SQLException;

}
