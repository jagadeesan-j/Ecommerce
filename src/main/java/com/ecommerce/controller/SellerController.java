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
import com.ecommerce.model.Product;
import com.ecommerce.model.Seller;
import com.ecommerce.service.SellerService;

@RestController
@RequestMapping("/Seller")
public class SellerController {

	@Autowired
	SellerService sellerservice;

	@GetMapping("/login")
	public ResponseEntity<?> loginSeller(@RequestBody Seller s) {
		return new ResponseEntity<>(sellerservice.login(s), HttpStatus.OK);
	}

	@GetMapping("/view")
	public ResponseEntity<?> viewseller() {
		return new ResponseEntity<>(sellerservice.viewseller(), HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateSeller(@RequestBody Seller s) {
		return new ResponseEntity<>(sellerservice.updateseller(s), HttpStatus.OK);
	}

	@GetMapping("/view/products")
	public ResponseEntity<?> viewProduct() {
		return new ResponseEntity<>(sellerservice.viewproduct(), HttpStatus.OK);
	}

	@GetMapping("/addproduct")
	public ResponseEntity<?> addproduct(@RequestBody Product p) {
		return new ResponseEntity<>(sellerservice.addproduct(p), HttpStatus.OK);
	}

	@GetMapping("/getproduct/{product_id}")
	public ResponseEntity<?> getProduct(@PathVariable("product_id") String product_id) {
		return new ResponseEntity<>(sellerservice.getproduct(product_id), HttpStatus.OK);
	}

	@PutMapping("/updateproduct/{product_id}")
	public ResponseEntity<?> updateProduct(@PathVariable("product_id") String product_id, @RequestBody Product p) {
		return new ResponseEntity<>(sellerservice.updateproduct(p, product_id), HttpStatus.OK);
	}

	@DeleteMapping("/deleteproduct/{product_id}")
	public ResponseEntity<?> deleteteProduct(@PathVariable("product_id") String product_id) {
		return new ResponseEntity<>(sellerservice.deleteproduct(product_id), HttpStatus.OK);
	}

	@GetMapping("/viewbuyerscart")
	public ResponseEntity<?> viewbuyers() {
		return new ResponseEntity<>(sellerservice.viewbuyerscart(), HttpStatus.OK);
	}

	@GetMapping("/logout")
	public ResponseEntity<?> logoutSeller() {
		return new ResponseEntity<>(sellerservice.logout(), HttpStatus.OK);
	}

}
