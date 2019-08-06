package abc.springmvc.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import abc.springmvc.dao.CustomerDAO;
import abc.springmvc.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void saveCustomer(Customer customer) {

		customerDAO.saveCustomer(customer);

	}

	@Override
	@Transactional
	public void deleteCustomer(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional
	public Customer getCustomer(String username) {

		customerDAO.getCustomer(username);
		return null;
	}

}
