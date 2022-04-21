package com.ecommerce.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository repo;

	public String saveProduct(Product p) {
		repo.save(p);
		return "Your product is added.";
	}

	public List<Product> fetchProductList() {
		List<Product> list = repo.findAll();
		return list;
	}

	public Product updateProduct(Product p, String pid) {
		Product p1 = repo.getById(pid);
		p1.setProduct_name(p.getProduct_name());
		p1.setProduct_category(p.getProduct_category());
		p1.setProduct_price(p.getProduct_price());
		return p1;
	}

	@Override
	public String deleteProductById(String pid) {
		repo.deleteById(pid);
		return "Product Id:" + pid + " is deleted.";
	}

	@Override
	public Product getProductById(String pid) {
		Product p1 = repo.getById(pid);
		return p1;
	}
}
