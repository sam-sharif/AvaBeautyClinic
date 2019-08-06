package abc.springmvc.controller;

import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import abc.springmvc.entity.Customer;
import abc.springmvc.service.CustomerService;
import abc.springmvc.user.CrmUser;

@Controller
public class AppController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/")
	public String showLandingPage(Authentication authentication, HttpServletRequest request) {
		
		if(authentication != null && authentication.isAuthenticated()) {
			String userName = authentication.getName();
			request.getSession().setAttribute("usernameOverApp", userName);
		}
		
		return "LandingPage";
	}
	
	
	
	@GetMapping("showMyLoginPage")
	public String showLoginPage() {
		return "FancyLogin";
	}
	
	@GetMapping("signUp")
	public String showSigRegistrationForm(Model model) {
		CrmUser user = new CrmUser();
		model.addAttribute("crmUser",user);
		return "RegisterationForm1";
	}
	
	@GetMapping("access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}
	
	@GetMapping("myProfile")
	public String showMyProfile(HttpSession session, Model model) {
		
		String userName = (String) session.getAttribute("usernameOverApp");
		Customer customer = customerService.getCustomer(userName);
		model.addAttribute("customer", customer);
		return "CustomerProfile";
		
	}
	
	
	
}
