package abc.springmvc.entity;



import java.util.Date;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date appointmentDate;
	
	private Integer appointmentTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date appliedDate;
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
			fetch = FetchType.EAGER)
	@JoinColumn(name="customer_id")
	private Customer customer;

		
	public Appointment() {
		super();
	}
	
	

	


	public Appointment(Date appointmentDate, Integer appointmentTime, Date appliedDate) {
		super();
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.appliedDate = appliedDate;
	}






	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	

	

	
	public Date getAppointmentDate() {
		return appointmentDate;
	}






	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}






	public Integer getAppointmentTime() {
		return appointmentTime;
	}






	public void setAppointmentTime(Integer appointmentTime) {
		this.appointmentTime = appointmentTime;
	}






	



	public Date getAppliedDate() {
		return appliedDate;
	}






	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}






	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
