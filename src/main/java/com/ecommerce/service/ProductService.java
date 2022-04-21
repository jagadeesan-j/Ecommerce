package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Product;

@Service
public interface ProductService {

	String saveProduct(Product p);

	List<Product> fetchProductList();

	Product updateProduct(Product p, String pid);

	String deleteProductById(String pid);

	Product getProductById(String pid);
}
