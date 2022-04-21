package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Cart;
import com.ecommerce.model.Product;
import com.ecommerce.model.Seller;
import com.ecommerce.repository.SellerRepository;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	SellerRepository sellerrepo;

	@Autowired
	SellerService sellerservice;

	@Autowired
	ProductService productservice;

	@Autowired
	CartService cartservice;
	private String selstatus = "";
	private String seller = "";

	@Override
	public void saveSeller(Seller s) {
		sellerrepo.save(s);
	}

	@Override
	public List<Seller> fetchSellerList() {
		List<Seller> list = sellerrepo.findAll();
		return list;
	}

	@Override
	public Seller updateSeller(Seller s, String seller_id) {
		Seller s1 = sellerrepo.getById(seller_id);
		s1.setSeller_name(s.getSeller_name());
		s1.setSeller_email(s.getSeller_email());
		s1.setSeller_phone_number(s.getSeller_phone_number());
		s1.setSeller_address(s.getSeller_address());
		return s1;
	}

	@Override
	public String deleteSellerById(String seller_id) {
		sellerrepo.deleteById(seller_id);
		return "Seller Id:" + seller_id + " is deleted.";
	}

	@Override
	public Seller getSellerById(String seller_id) {
		Seller s1 = sellerrepo.getById(seller_id);
		return s1;
	}

	@Override
	public boolean existsById(String seller_id) {
		Seller s1 = sellerrepo.getById(seller_id);
		if (s1.getSeller_id().matches(seller_id))
			return true;
		else
			return false;
	}

	@Override
	public String login(Seller s) {
		try {
			if (sellerrepo.existsById(s.getSeller_id())) {
				if (sellerrepo.getById(s.getSeller_id()).getSeller_password().matches(s.getSeller_password())) {
					System.out.println("Login Successful");
					selstatus = "Login";
					seller = s.getSeller_id();
					return "Login Successful";
				} else
					throw new Exception("Seller Password is Incorrect");
			} else
				throw new Exception("SellerId is not existed.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String viewseller() {
		try {
			if (selstatus.matches("Login"))
				return sellerservice.getSellerById(seller).toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String updateseller(Seller s) {
		try {
			if (selstatus.matches("Login")) {
				if (s.getSeller_id().matches(seller))
					return updateSeller(s, seller).toString();
				else
					throw new Exception("Your Customer Id is wrong.");
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String viewproduct() {
		try {
			if (selstatus.matches("Login")) {
				List<Product> plist1 = new ArrayList<>();
				if (productservice.fetchProductList() != null) {
					for (Product p : productservice.fetchProductList())
						if (p.getSeller_id().matches(seller))
							plist1.add(p);
				} else
					throw new Exception("You have no products.");
				return plist1.toString();
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String addproduct(Product p) {
		try {
			if (selstatus.matches("Login")) {
				if (p.getSeller_id().matches(seller))
					return productservice.saveProduct(p).toString();
				else
					throw new Exception("Seller Id is wrong.");
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String getproduct(String product_id) {
		try {
			if (selstatus.matches("Login"))
				return productservice.getProductById(product_id).toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String updateproduct(Product p, String product_id) {
		try {
			if (selstatus.matches("Login"))
				return productservice.updateProduct(p, product_id).toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String deleteproduct(String product_id) {
		try {
			if (selstatus.matches("Login"))
				return productservice.deleteProductById(product_id).toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return e.getMessage();
		}
	}

	@Override
	public String viewbuyerscart() {
		try {
			if (selstatus.matches("Login")) {
				List<Cart> clist1 = new ArrayList<>();
				if (cartservice.fetchCartList() != null) {
					for (Cart c : cartservice.fetchCartList())
						if (c.getSeller_id().matches(seller))
							clist1.add(c);
					return clist1.toString();
				} else
					throw new Exception("Buyers cart is empty");
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String logout() {
		try {
			if (selstatus.matches("Login")) {
				System.out.println("Logout Successful");
				selstatus = "";
				seller = "";
				return "Logout Successful";
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
