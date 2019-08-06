package abc.springmvc.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.Message;
import javax.persistence.TemporalType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import abc.springmvc.entity.Appointment;
import abc.springmvc.entity.Customer;
import abc.springmvc.smtp.MailSender;

@Repository
public class AppointmentDAOImpl implements AppointmentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private MailSender mailSender;
	
	@Override
	public List<Appointment> getAppointments() {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Appointment");
		List<Appointment> appointments = query.getResultList();
		return appointments;
	}

	@Override
	public void saveAppointment(Appointment appointment , String customerUsername) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Customer where username = :theUsername");
		query.setParameter("theUsername", customerUsername);
		Customer theCustomer = (Customer) query.getSingleResult();
		theCustomer.addAppointment(appointment);
		appointment.setCustomer(theCustomer);
		session.saveOrUpdate(appointment);
		
		//setting email sender
	/*  String time = null;
		switch (appointment.getTime()) {
		case 1: time = "10am - 12pm";
			break;
		case 2: time = "12pm - 2pm";
		break;
		case 3: time = "2pm - 4pm";
		break;
		case 4: time = "4pm - 6pm";
		break;
		default:
			break;
		}
		mailSender.send("samir1982sharif@gmail.com", "sam1361022222", "samir_sharif@yahoo.com", "New Appointment",
				"<html>\r\n" + 
				"<body>\r\n" + 
				"<h1>Hello Maryam there is a new Appointment </h1>\r\n "+
				"<h2>Name: "+theCustomer.getFirstName()+"</h2>\r\n" + 
				"<h2>LastName: "+theCustomer.getLastName()+"</h2>\r\n" +
				"<h2>Phone: "+theCustomer.getPhoneNo()+"</h2>\r\n" +
				"<h2>Date: "+appointment.getDate().toString()+"</h2>\r\n" +
				"<h2>Time: "+time+"</h2>\r\n" +
				"</body>\r\n" + 
				"</html> ");
	*/
	}

	@Override
	public List<Appointment> getAppointments(Date date_from, Date date_to) {

		Session session = sessionFactory.getCurrentSession();
		
		List<Appointment> appointments = null;
		
		if(date_from != null && date_to != null) {
		Query q = session.createQuery("From Appointment A where A.appointmentDate >= :dateFrom and A.appointmentDate <= :dateTo  ");
		q.setParameter("dateFrom",date_from,TemporalType.DATE);
		q.setParameter("dateTo",date_to,TemporalType.DATE);
		appointments = q.getResultList();
		}else if (date_from != null && date_to == null) {
			Query q = session.createQuery("From Appointment A where A.appointmentDate = :dateFrom ");
			q.setParameter("dateFrom",date_from,TemporalType.DATE);
			appointments = q.getResultList();
		}
		else {
			appointments = session.createQuery("from Appointment").getResultList();
		}
		return appointments;
	}

	@Override
	public List<Appointment> searchAppointment(String customerName) {

		Session session = sessionFactory.getCurrentSession();
		List<Appointment> appointments = null;
		
		if(!customerName.isEmpty()) {
			Query query = session.createQuery("From Appointment where customer.firstName like :theItem or customer.lastName like :theItem");
			query.setParameter("theItem", "%"+customerName+"%");
			appointments = query.getResultList();
			}else {
				appointments = session.createQuery("from Appointment").getResultList(); 
			}
		
		
		return appointments;
		
	}

	@Override
	public List<Appointment> getSavedAppointments() {

		Session session = sessionFactory.getCurrentSession();
		
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_MONTH, 15);
		Date modifiedDate = cal.getTime();
		
		Query q = session.createQuery("From Appointment A where A.appointmentDate >= :dateFrom and A.appointmentDate <= :dateTo  ");
		q.setParameter("dateFrom",now,TemporalType.DATE);
		q.setParameter("dateTo",modifiedDate,TemporalType.DATE);
		List<Appointment> appointments = q.getResultList();
		return appointments;
	}

}
