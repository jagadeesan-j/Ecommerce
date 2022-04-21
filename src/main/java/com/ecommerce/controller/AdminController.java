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

import com.ecommerce.model.Admin;
import com.ecommerce.service.AdminService;
import com.ecommerce.model.Seller;

@RestController
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	AdminService adminservice;

	@RequestMapping("/test")
	public String test() {
		return "Test Admin Controller";
	}

	@RequestMapping("/register")
	public ResponseEntity<?> registerAdmin(@RequestBody Admin a) {
		return new ResponseEntity<>(adminservice.register(a), HttpStatus.OK);
	}

	@GetMapping("/login")
	public ResponseEntity<?> loginAdmin(@RequestBody Admin a) {
		return new ResponseEntity<>(adminservice.login(a), HttpStatus.OK);
	}

	@RequestMapping("/view/admins")
	public ResponseEntity<?> listAdmin() {
		return new ResponseEntity<>(adminservice.listadmin(), HttpStatus.OK);
	}

	@GetMapping("/getadmin/{adminid}")
	public ResponseEntity<?> findAdmin(@PathVariable("adminid") String adminid) {
		return new ResponseEntity<>(adminservice.getadmin(adminid), HttpStatus.OK);
	}

	@PutMapping("/updateadmin")
	public ResponseEntity<?> updateAdmin(@RequestBody Admin a) {
		return new ResponseEntity<>(adminservice.updateadmin(a), HttpStatus.OK);
	}

	@DeleteMapping("/deleteadmin")
	public ResponseEntity<?> deleteAdmin() {
		return new ResponseEntity<>(adminservice.deleteadmin(), HttpStatus.OK);
	}

	@RequestMapping("/view/customers")
	public ResponseEntity<?> listCustomers() {
		return new ResponseEntity<>(adminservice.listcustomers(), HttpStatus.OK);
	}

	@RequestMapping("/getcustomer/{CustomerId}")
	public ResponseEntity<?> findCustomer(@PathVariable("CustomerId") String CustomerId) {
		return new ResponseEntity<>(adminservice.getcustomer(CustomerId), HttpStatus.OK);
	}

	@GetMapping("/view/sellers")
	public ResponseEntity<?> viewsellerproduct() {
		return new ResponseEntity<>(adminservice.viewseller(), HttpStatus.OK);
	}

	@GetMapping("/addseller")
	public ResponseEntity<?> addseller(@RequestBody Seller s) {
		return new ResponseEntity<>(adminservice.addseller(s), HttpStatus.OK);
	}

	@GetMapping("/getseller/{seller_id}")
	public ResponseEntity<?> getSeller(@PathVariable("seller_id") String seller_id) {
		return new ResponseEntity<>(adminservice.getseller(seller_id), HttpStatus.OK);
	}

	@GetMapping("/view/products")
	public ResponseEntity<?> viewProduct() {
		return new ResponseEntity<>(adminservice.viewproduct(), HttpStatus.OK);
	}

	@GetMapping("/view/sellerproduct/{seller_id}")
	public ResponseEntity<?> viewsellerproduct(@PathVariable("seller_id") String seller_id) {
		return new ResponseEntity<>(adminservice.viewsellerproduct(seller_id), HttpStatus.OK);
	}

	@DeleteMapping("/deleteseller/{seller_id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("seller_id") String seller_id) {
		return new ResponseEntity<>(adminservice.deleteseller(seller_id), HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logoutAdmin() {
		return new ResponseEntity<>(adminservice.logout(), HttpStatus.OK);
	}
}
