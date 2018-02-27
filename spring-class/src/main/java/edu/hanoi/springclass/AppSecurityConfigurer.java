package edu.hanoi.springclass;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.AuthenticationManagerConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.WebSecurityEnablerConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import edu.hanoi.controller.HomeController;
@EnableWebSecurity
@Configuration

public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter{
	private final static Logger logger = Logger.getLogger(AppSecurityConfigurer.class);
	

    public AppSecurityConfigurer() {
        super();
    }
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("abc").password("abc").roles("USER");
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
	        .authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/log").permitAll()
		.antMatchers("/nguoi-dung/**").hasAnyRole("USER")
		.anyRequest().authenticated()
		.and()
		.formLogin()
        .loginPage("/login")
        .loginProcessingUrl("/login")
		.usernameParameter("username")
		.passwordParameter("password")
		.defaultSuccessUrl("/nguoi-dung",true)
		.failureUrl("/login?error")
		.and()
		.exceptionHandling()
		.accessDeniedPage("/403")
		.and().logout();
		;
		
		logger.info("dang lay log");
	}
}
