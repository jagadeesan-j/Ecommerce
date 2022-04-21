package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Product;
import com.ecommerce.model.Seller;

@Service
public interface SellerService {

	void saveSeller(Seller seller);

	List<Seller> fetchSellerList();

	Seller updateSeller(Seller seller, String seller_id);

	String deleteSellerById(String seller_id);

	Seller getSellerById(String seller_id);

	boolean existsById(String seller_id);

	String login(Seller s);

	String viewseller();

	String updateseller(Seller s);

	String viewproduct();

	String addproduct(Product p);

	String getproduct(String pid);

	String updateproduct(Product p, String pid);

	String deleteproduct(String pid);

	String viewbuyerscart();

	String logout();
}
