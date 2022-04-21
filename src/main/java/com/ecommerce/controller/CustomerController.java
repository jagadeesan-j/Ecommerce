package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.model.Customer;
import com.ecommerce.service.CustomerService;

@RestController
@RequestMapping("/Customer")
public class CustomerController {

	@Autowired
	CustomerService customerservice;

	@RequestMapping("/test")
	public String test() {
		return "Test Customer Controller";
	}

	@GetMapping("/register")
	public ResponseEntity<?> registerCustomer(@RequestBody Customer c) {
		return new ResponseEntity<>(customerservice.regiser(c), HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<?> loginCustomer(@RequestBody Customer c) {
		return new ResponseEntity<>(customerservice.logincustomer(c), HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<?> viewcustomer() {
		return new ResponseEntity<>(customerservice.viewcustomer(), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateCustomer(@RequestBody Customer c) {
		return new ResponseEntity<>(customerservice.updatecustomer(c), HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteCustomer() {
		return new ResponseEntity<>(customerservice.deletecustomer(), HttpStatus.OK);
	}

	@GetMapping("/view/products")
	public ResponseEntity<?> viewProduct() {
		return new ResponseEntity<>(customerservice.viewproduct(), HttpStatus.OK);
	}

	@GetMapping("/selectproduct/{product_id}")
	public ResponseEntity<?> selectProduct(@PathVariable("product_id") String product_id) {
		return new ResponseEntity<>(customerservice.selectproduct(product_id), HttpStatus.OK);
	}

	@RequestMapping("/view/cart")
	public ResponseEntity<?> viewcart() {
		return new ResponseEntity<>(customerservice.viewcart(), HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logoutCustomer() {
		return new ResponseEntity<>(customerservice.logout(), HttpStatus.OK);
	}
}
