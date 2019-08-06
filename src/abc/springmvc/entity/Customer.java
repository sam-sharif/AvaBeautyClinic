package abc.springmvc.entity;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotBlank(message = "This Field Is Mandatory")
	@Column(name="first_name")
	private String firstName;
	
	@NotBlank(message = "This Field Is Mandatory")
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="usename")
	private String username;
	
	@NotBlank(message = "This Field Is Mandatory")
	@Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$",message = "Wrong Email Format")
	@Column(name="email")
	private String email;
	
	@NotBlank(message = "This Field Is Mandatory")
	@Pattern(regexp = "^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$",message = "i.e. (123) 456-7890")
	@Column(name = "phone_no")
	private String phoneNo;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "customer")
	private List<Appointment> appointments;

	

	public Customer() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public void addAppointment(Appointment appointment) {
		
		if (appointments == null) {
			appointments = new ArrayList<>();
		}
		
		appointments.add(appointment);
	}
	
	
	
	
	
	
	
	
}
