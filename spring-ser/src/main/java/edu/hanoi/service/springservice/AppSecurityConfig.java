package edu.hanoi.service.springservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.Authentication;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private HnUserAuthProvider hnUserAuthProvider;
	
	 public AppSecurityConfig() {
	        super();
	    }
	 
	 @Autowired
	  public void configureGlobal(AuthenticationManagerBuilder auth) {
	    try {
	    	auth.authenticationProvider(hnUserAuthProvider);
	    	
			auth
			  .inMemoryAuthentication()
			    .withUser("abc")  // #1
			      .password("abc")
			      .roles("USER","ADMIN")
			      .and()
			    .withUser("admin") // #2
			      .password("password")
			      .roles("ADMIN","USER");
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	 
	
	private AuthenticationProvider customAuthenticationProvider() {
		AuthenticationProvider provider = new HnUserAuthProvider();
		return provider;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/").hasRole("USER").anyRequest().authenticated().and().httpBasic();
	}
	
	
}
