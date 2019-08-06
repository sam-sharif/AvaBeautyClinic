package abc.springmvc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public DataSource securityDataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

				
		auth.jdbcAuthentication().dataSource(securityDataSource);

	} 
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		    .antMatchers("/").permitAll()
			.antMatchers("/showMyLoginPage").permitAll()
			.antMatchers("/signUp").permitAll()
			.antMatchers("/register/**").permitAll()
			.antMatchers("/customer/delete*").hasRole("ADMIN")
			.antMatchers("/appointment/**").hasRole("CUSTOMER")
			.antMatchers("/resources/**").permitAll()
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.permitAll()
			.and()
			.logout().logoutSuccessUrl("/").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
	
	//UserDetailsManager is a class for doing CRUD operations over a user and has 5 methods such as createUser, changePassword,
	//deleteUser, userExist
	@Bean
	public UserDetailsManager userDetailsManager() {
		
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		
		jdbcUserDetailsManager.setDataSource(securityDataSource);
		
		return jdbcUserDetailsManager; 
	}
}
