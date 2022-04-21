package com.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.model.Admin;
import com.ecommerce.model.Seller;

@Service
public interface AdminService {

	void saveAdmin(Admin a);

	List<Admin> fetchAdminList();

	Admin updateAdmin(Admin a, String admin_id);

	void deleteAdminById(String admin_id);

	Admin getAdminById(String admin_id);

	String register(Admin a);

	String login(Admin a);

	String listadmin();

	String getadmin(String adminid);

	String updateadmin(Admin a);

	String deleteadmin();

	String listcustomers();

	String getcustomer(String CustomerId);

	String viewseller();

	String addseller(Seller s);

	String getseller(String sellerid);

	String viewsellerproduct(String sellerid);

	String deleteseller(String sellerid);

	String viewproduct();

	String logout();

}
