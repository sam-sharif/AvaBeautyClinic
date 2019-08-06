package abc.springmvc.dao;

import java.util.List;

import abc.springmvc.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();
	public Customer getCustomer(int id);
	public void saveCustomer(Customer customer);
	public void deleteCustomer(int id);
	public Customer getCustomer(String username);
	
}
