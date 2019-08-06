package abc.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import abc.springmvc.entity.Appointment;
import abc.springmvc.service.AppointmentService;

@Controller
@RequestMapping("customer")
public class OwnerController {

	@Autowired
	private AppointmentService appointmentService;
	
	@RequestMapping("appointmentList")
	public String showAppointmentList(Model model) {

		List<Appointment> appointments = appointmentService.getAppointments();
		model.addAttribute("appointments", appointments);
		return "AppointmentList";
	}
}
