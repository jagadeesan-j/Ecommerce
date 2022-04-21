package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Customer;

@Service
public interface CustomerService {

	void saveCustomer(Customer c);

	List<Customer> fetchCustomerList();

	Customer updateCustomer(Customer c, String customer_id);

	void deleteCustomerById(String customer_id);

	Customer getCustomerById(String customer_id);

	String regiser(Customer c);

	String logincustomer(Customer c);

	String viewcustomer();

	String updatecustomer(Customer c);

	String deletecustomer();

	String viewproduct();

	String selectproduct(String pid);

	String viewcart();

	String logout();
}
