package abc.springmvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import abc.springmvc.entity.Customer;
import abc.springmvc.service.CustomerService;
import abc.springmvc.user.CrmUser;

@Controller
@RequestMapping("register")
public class RegisterationController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserDetailsManager userDetailsManager;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@PostMapping("processRegisterationForm1")
	public String showprocessRegistrationForm1(@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
			BindingResult theBindingResult,
			HttpServletRequest request,
			Model theModel) {

		if (theBindingResult.hasErrors()) {

			theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name/password can not be empty.");

			return "RegisterationForm1";	
		}
		
		String userName = theCrmUser.getUserName();

		boolean userExists = doesUserExist(userName);

		if (userExists) {
			//theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");

			return "RegisterationForm1";			
		}
		//validation is done now we create new user
		String encodedPassword = passwordEncoder.encode(theCrmUser.getPassword());

        encodedPassword = "{bcrypt}" + encodedPassword;
                 
		// give user default role of "customer"
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_CUSTOMER");

        // create user object (from Spring Security framework)
        User tempUser = new User(userName,encodedPassword,authorities);

        // redirecting user to RegistrationForm2 to complete the info
        request.getSession().setAttribute("userModel", tempUser);
        	Customer newCustomer = new Customer();
        	newCustomer.setUsername(userName);
        	theModel.addAttribute("newCustomer", newCustomer);
		
        
        
        return "RegisterationForm2";
	
	}

	
	@PostMapping("processRegisterationForm2")
	public String showprocessRegistrationForm2(@Valid @ModelAttribute("newCustomer") Customer theCustomer,
			BindingResult theBindingResult,
			HttpServletRequest request,
			Model theModel) {
		
		if (theBindingResult.hasErrors()) {

			theModel.addAttribute("newCustomer", theCustomer);
			
			return "RegisterationForm2";	
		}
				
		//save CrmUser Info of the customer
		
		User tempUser = (User) request.getSession().getAttribute("userModel");
		userDetailsManager.createUser(tempUser);
		
		// create new customer
		customerService.saveCustomer(theCustomer);
		theModel.addAttribute("customerName", theCustomer);
		
		//setting username over app 
		request.getSession().setAttribute("usernameOverApp", tempUser.getUsername());
		
		return "RegisterationConfirmation";
	}

	private boolean doesUserExist(String userName) {

		// check the database if the user already exists
		boolean exists = userDetailsManager.userExists(userName);

		return exists;
	}
}
