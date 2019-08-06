package abc.springmvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import abc.springmvc.entity.Appointment;
import abc.springmvc.entity.Customer;
import abc.springmvc.service.AppointmentService;
import abc.springmvc.service.CustomerService;
import abc.springmvc.service.CustomerServiceImpl;
import abc.springmvc.user.CrmUser;

@Controller
@RequestMapping("appointment")
public class AppoinmentController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AppointmentService appointmentService;

	@GetMapping("makeAppointmentForm")
	public String showMakeAppointmentForm(Authentication authentication,Model model,
			HttpSession session) {
		
		if( authentication != null && authentication.isAuthenticated()) {
			String userName = authentication.getName();
			session.setAttribute("usernameOverApp", userName);
		}
		List<Appointment> appointments = appointmentService.getSavedAppointments();
		model.addAttribute("savedList", appointments);
		return "MakeAppointment";
	}
	

	@RequestMapping(value = "/appointmentProccessing",method = {RequestMethod.GET,RequestMethod.POST})
	public String appointmentProccessing(HttpServletRequest request,
			HttpSession session,
			Model theModel) {
		
		String userName = (String) session.getAttribute("usernameOverApp");
		String theDate = request.getParameter("date");
		String theTime1 = request.getParameter("time");
		
		List<Appointment> appointments = appointmentService.getSavedAppointments();
		theModel.addAttribute("savedList", appointments);
		
		if(theDate.isEmpty() || theTime1.equals("NONE")) {
			request.setAttribute("error", "Date and Time Must Be Picked");
			return "MakeAppointment";
		}
		

		Date pickedDate = null;
		try {
		   
			//converting from String to Date
		    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");//converting from String to Date
		    pickedDate =sdf.parse(theDate);
		    
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		//checking if the picked time is not past
		Date now = new Date();
		if(!pickedDate.after(now)) {
			request.setAttribute("error", "You Picked a Past Date");
			return "MakeAppointment";
		}
		
		//checking if the picked time is not further than 14 days
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(Calendar.DAY_OF_MONTH, 14);
		Date modifiedDate = cal.getTime();
		if(pickedDate.after(modifiedDate)) {
			request.setAttribute("error", "You can not pick a date further than 14 days.");
			return "MakeAppointment";
		}
		
		
		//if theTime is not null then parse it
		Integer theTime = Integer.parseInt(theTime1); 
				
		Appointment appointment = new Appointment();
		appointment.setAppointmentDate(pickedDate);
		appointment.setAppointmentTime(theTime);
		appointment.setAppliedDate(new Date());
		
		appointmentService.saveAppointment(appointment, userName);
		
		theModel.addAttribute("name", userName);
		theModel.addAttribute("date", theDate);
		theModel.addAttribute("time", theTime);
		

		return "AppointmentSuccess";
	}
	@GetMapping("appointmentList")
	public String showAppointmentList(Model model) {
		
		List<Appointment> appointments = appointmentService.getAppointments();
		
		model.addAttribute("appointments",appointments);
		return "AppointmentList";
	}
	
	@GetMapping("appointmentSearchList")
	public String showAppointmentSearchList(Model model,HttpServletRequest request,
			@RequestParam("action") String action) {
		
		List<Appointment> appointments = new ArrayList<Appointment>();
		
		switch (action) {
		
		case "customerSearch":
			String searchItem = request.getParameter("searchItem");
			appointments = appointmentService.searchAppointment(searchItem);
			break;
			
		case "dateSearch":
			String date_from = request.getParameter("date_from");
			String date_to = request.getParameter("date_to");
			Date Date_From = null;
			Date Date_To = null;
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");//converting from String to Date
			
			
				try {
					if(!(date_from.isEmpty())) {
					Date_From =sdf.parse(date_from);
					}
					
					if(!(date_to.isEmpty())) {
						Date_To = sdf.parse(date_to);
					}
				} catch (ParseException e) {e.printStackTrace();}
			
						    
			    appointments = appointmentService.getAppointments(Date_From, Date_To);
			   				
			
			break;
		
		}

		model.addAttribute("appointments",appointments);
		return "AppointmentList";
	}
	
	


}
