package abc.springmvc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import abc.springmvc.dao.AppointmentDAO;
import abc.springmvc.entity.Appointment;
import abc.springmvc.entity.Customer;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentDAO appointmentDAO;

	@Override
	@Transactional
	public List<Appointment> getAppointments() {


		return appointmentDAO.getAppointments();
	}

	@Override
	@Transactional
	public void saveAppointment(Appointment appointment , String customerUsername) {

		appointmentDAO.saveAppointment(appointment,customerUsername);

	}

	@Override
	@Transactional
	public List<Appointment> getAppointments(Date date_from, Date date_to) {

		
		return appointmentDAO.getAppointments(date_from, date_to);
	}

	@Override
	@Transactional
	public List<Appointment> searchAppointment(String customerName) {
		
		return appointmentDAO.searchAppointment(customerName);
	}

	@Override
	@Transactional
	public List<Appointment> getSavedAppointments() {


		return appointmentDAO.getSavedAppointments();
	}

}
