package abc.springmvc.service;

import java.util.Date;
import java.util.List;

import abc.springmvc.entity.Appointment;
import abc.springmvc.entity.Customer;

public interface AppointmentService {
	
	public List<Appointment> getAppointments();
	public void saveAppointment(Appointment appointment,String customerUsername);
	public List<Appointment> getAppointments(Date date_from, Date date_to);
	public List<Appointment> searchAppointment(String customerName);
	public List<Appointment> getSavedAppointments();
}
