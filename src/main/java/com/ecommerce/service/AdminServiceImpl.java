package com.ecommerce.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Admin;
import com.ecommerce.model.Seller;
import com.ecommerce.repository.AdminRepository;
import com.ecommerce.repository.SellerRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminrepo;

	@Autowired
	CustomerService customerservice;

	@Autowired
	SellerService sellerservice;

	@Autowired
	SellerRepository sellerrepo;

	@Autowired
	ProductService productservice;
	private String adstatus = "";
	private String admin = "";

	@Override
	public void saveAdmin(Admin a) {
		adminrepo.save(a);
	}

	@Override
	public List<Admin> fetchAdminList() {
		return adminrepo.findAll();
	}

	@Override
	public Admin updateAdmin(Admin a, String admin_id) {
		Admin a1 = adminrepo.getById(admin_id);
		a1.setAdmin_name(a.getAdmin_name());
		a1.setAdmin_email(a.getAdmin_email());
		a1.setAdmin_phone_number(a.getAdmin_phone_number());
		a1.setAdmin_password(a.getAdmin_password());
		return a1;
	}

	@Override
	public void deleteAdminById(String admin_id) {
		adminrepo.deleteById(admin_id);
	}

	@Override
	public Admin getAdminById(String admin_id) {
		return adminrepo.getById(admin_id);
	}

	@Override
	public String register(Admin a) {
		try {
			if (!adminrepo.existsById(a.getAdmin_id())) {
				if (a.getAdmin_id().matches("[a-zA-Z]{5}[0-9]{3}")) {
					if (a.getAdmin_phone_number().length() == 10 && a.getAdmin_phone_number().matches("[0-9]{10}")) {
						if (a.getAdmin_email().matches("[A-Za-z0-9~!@#$%^&*()_+{}:<>?|]{3,10}" + "@gmail.com")) {
							if (a.getAdmin_password().matches(
									"^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8}$")
									&& a.getAdmin_password().length() == 8) {
								saveAdmin(a);
								return "Registered Successfully";
							} else
								throw new Exception("Enter the vaild Password");
						} else
							throw new Exception("Enter the vaild Email Id");
					} else
						throw new Exception("Enter the valid phonenumber");
				} else
					throw new Exception("Enter the valid AdminId. eg:Abcde123");
			} else
				throw new Exception("This Admin Id is already exist.Create another");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String login(Admin a) {
		try {
			if (adminrepo.existsById(a.getAdmin_id())) {
				if (adminrepo.getById(a.getAdmin_id()).getAdmin_password().matches(a.getAdmin_password())) {
					System.out.println("Login Successful");
					adstatus = "Login";
					admin = a.getAdmin_id();
					return "Login Successful";
				} else

					throw new Exception("Admin Password is Incorrect.");
			} else
				throw new Exception("AdminId is not existed.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String listadmin() {
		try {
			if (adstatus.matches("Login"))
				return fetchAdminList().toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String getadmin(String adminid) {
		try {
			if (adstatus.matches("Login")) {
				Admin a = getAdminById(adminid);
				if (a != null)
					return a.toString();
				else
					return "AdminId is not found";
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String updateadmin(Admin a) {
		try {
			if (adstatus.matches("Login"))
				return updateAdmin(a, admin).toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String deleteadmin() {
		try {
			if (adstatus.matches("Login")) {
				deleteAdminById(admin);
				adstatus = "";
				admin = "";
				return "Your Admin Id is deleted.";
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();

		}
	}

	@Override
	public String listcustomers() {
		try {
			if (adstatus.matches("Login"))
				return customerservice.fetchCustomerList().toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String getcustomer(String CustomerId) {
		try {
			if (adstatus.matches("Login")) {
				if (customerservice.getCustomerById(CustomerId) != null) {
					return customerservice.getCustomerById(CustomerId).toString();
				} else
					return "CustomerId is not found.";
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String viewseller() {
		try {
			if (adstatus.matches("Login"))
				return sellerservice.fetchSellerList().toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String addseller(Seller s) {
		try {
			if (!sellerrepo.existsById(s.getSeller_id())) {
				if (s.getSeller_id().matches("[a-zA-Z]{5}[0-9]{3}")) {
					if (s.getSeller_phone_number().length() == 10 && s.getSeller_phone_number().matches("[0-9]{10}")) {
						if (s.getSeller_email().matches("[A-Za-z0-9~!@#$%^&*()_+{}:<>?|]{3,10}" + "@gmail.com")) {
							if (s.getSeller_password().matches(
									"^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8}$")
									&& s.getSeller_password().length() == 8) {
								sellerservice.saveSeller(s);
								return "Seller addedd Successfully";
							} else
								throw new Exception("Enter the vaild Password");
						} else
							throw new Exception("Enter the vaild Email Id");
					} else
						throw new Exception("Enter the valid phonenumber");
				} else
					throw new Exception("Enter the valid SellerId. eg:Abcde123");
			} else
				throw new Exception("This Seller Id is already exist.Create another");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String getseller(String sellerid) {
		try {
			if (adstatus.matches("Login"))
				return sellerservice.getSellerById(sellerid).toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String viewproduct() {
		try {
			if (adstatus.matches("Login"))
				return productservice.fetchProductList().toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String viewsellerproduct(String sellerid) {
		try {
			if (adstatus.matches("Login"))
				return productservice.fetchProductList().stream().filter(p -> p.getSeller_id() == sellerid)
						.collect(Collectors.toList()).toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String deleteseller(String sellerid) {
		try {
			if (adstatus.matches("Login"))
				return sellerservice.deleteSellerById(sellerid).toString();
			else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@Override
	public String logout() {
		try {
			if (adstatus.matches("Login")) {
				adstatus = "";
				admin = "";
				return "Logout Successful";
			} else
				throw new Exception("You have to Login.");
		} catch (Exception e) {
			return e.getMessage();
		}

	}

}
