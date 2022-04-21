package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Cart;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerrepo;

	@Autowired
	ProductService productservice;

	@Autowired
	CartService cartservice;

	@Autowired
	CustomerService customerservice;

	@Autowired
	SellerService sellerservice;

	@Autowired
	JdbcTemplate jd;

	private String cusstatus = "";
	private String customer = "";

	@Override
	public void saveCustomer(Customer c) {
		customerrepo.save(c);
	}

	@Override
	public List<Customer> fetchCustomerList() {
		List<Customer> list = customerrepo.findAll();
		return list;
	}

	@Override
	public Customer updateCustomer(Customer c, String CustomerId) {
		Customer c1 = customerrepo.getById(CustomerId);
		c1.setCustomer_name(c.getCustomer_name());
		c1.setCustomer_address(c.getCustomer_address());
		c1.setCustomer_email(c.getCustomer_email());
		c1.setCustomer_phone_number(c.getCustomer_phone_number());
		c1.setCustomer_password(c.getCustomer_password());
		return c1;
	}

	@Override
	public void deleteCustomerById(String CustomerId) {
		customerrepo.deleteById(CustomerId);
	}

	@Override
	public Customer getCustomerById(String CustomerId) {
		Customer c1 = customerrepo.getById(CustomerId);
		return c1;
	}

	@Override
	public String regiser(Customer c) {
		try {
			if (!customerrepo.existsById(c.getCustomer_id())) {
				if (c.getCustomer_id().matches("[a-zA-Z]{5}[0-9]{3}")) {
					if (c.getCustomer_phone_number().length() == 10
							&& c.getCustomer_phone_number().matches("[0-9]{10}")) {
						if (c.getCustomer_email().matches("[A-Za-z0-9~!@#$%^&*()_+{}:<>?|]{3,10}" + "@gmail.com")) {
							if (c.getCustomer_password().matches(
									"^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8}$")
									&& c.getCustomer_password().length() == 8) {
								saveCustomer(c);
								return "Registered Successfully";
							} else
								throw new Exception("Enter the vaild Password. eg:Abc@1def");
						} else
							throw new Exception("Enter the vaild Email Id");
					} else
						throw new Exception("Enter the valid phonenumber");
				} else
					throw new Exception("Enter the valid CustomerId. eg:Abcde123");
			} else
				throw new Exception("This Customer Id is already exist.Create another");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String logincustomer(Customer c) {
		try {
			if (customerrepo.existsById(c.getCustomer_id())) {
				if (customerrepo.getById(c.getCustomer_id()).getCustomer_password().matches(c.getCustomer_password())) {
					System.out.println("Login Successful");
					cusstatus = "Login";
					customer = c.getCustomer_id();
					return "Login Successful";
				} else
					throw new Exception("Customer Password is Incorrect.");
			} else
				throw new Exception("CustomerId is not existed.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String viewcustomer() {
		try {
			if (cusstatus.matches("Login"))
				return getCustomerById(customer).toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String updatecustomer(Customer c) {
		try {
			if (cusstatus.matches("Login")) {
				if (c.getCustomer_id().matches(customer))
					return updateCustomer(c, customer).toString();
				else
					throw new Exception("Your Customer Id is wrong.");
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String deletecustomer() {
		try {
			if (cusstatus.matches("Login")) {
				deleteCustomerById(customer);
				cusstatus = "";
				customer = "";
				return "Your Customer Id is deleted.";
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String viewproduct() {
		try {
			if (cusstatus.matches("Login"))
				return productservice.fetchProductList().toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String selectproduct(String product_id) {
		try {
			if (cusstatus.matches("Login")) {
				int count = 0;
				String str = "select * from cart where customer_id='" + customer + "' and product_id='" + product_id
						+ "'";
				List<Cart> clist = jd.query(str, new BeanPropertyRowMapper<>(Cart.class));
				if (!clist.isEmpty())
					count = clist.get(0).getProduct_quantity();
				Product p = productservice.getProductById(product_id);
				cartservice.saveCart(product_id, p.getSeller_id(), p.getProduct_name(), p.getProduct_price(), customer,
						customerservice.getCustomerById(customer).getCustomer_name(),
						sellerservice.getSellerById(p.getSeller_id()).getSeller_name(), count + 1);
				return "Your selected product is added to your cart";
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String viewcart() {
		try {
			if (cusstatus.matches("Login")) {
				List<Cart> clist1 = new ArrayList<>();
				if (cartservice.fetchCartList() != null) {
					for (Cart c : cartservice.fetchCartList())
						if (c.getCustomer_id().matches(customer))
							clist1.add(c);
					return clist1.toString();
				} else
					return "No product in your cart.";
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String logout() {
		try {
			if (cusstatus.matches("Login")) {
				cusstatus = "";
				customer = "";
				return "Logout Successful";
			} else
				throw new Exception("You have to Login.");

		} catch (Exception e) {
			return e.getMessage();
		}
	}

}
