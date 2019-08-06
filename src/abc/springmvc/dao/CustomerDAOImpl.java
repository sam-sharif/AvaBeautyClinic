package abc.springmvc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import abc.springmvc.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveCustomer(Customer customer) {

		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(customer);



	}

	@Override
	public void deleteCustomer(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Customer getCustomer(String username) {
		if(username == null) {
			try {
				throw new Exception("the username is nullll");
			} catch (Exception e) {
				System.out.println(e.getMessage());

			}

		}
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Customer where username = :theUsername");
		query.setParameter("theUsername", username);
		Customer theCustomer = (Customer) query.getSingleResult();

		return theCustomer;
	}

	

}
