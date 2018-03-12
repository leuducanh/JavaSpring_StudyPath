package edu.hanoi.service.springservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import edu.hanoi.service.springservice.dao.UserDAO;
import edu.hanoi.service.springservice.dao.model.User;

@Component
public class HnUserAuthProvider implements AuthenticationProvider{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public Authentication authenticate(Authentication au) throws AuthenticationException {
		String username = au.getName().toString();
		User user = userDAO.get(username);
		
		if(user == null)return null;
		
		if(!user.getPassword().equals(au.getCredentials()))return null;
		return succesful(username,au.getCredentials().toString(),"ROLE_USER");
	}

	private UsernamePasswordAuthenticationToken succesful(String username, String pass, String role) {
		List<GrantedAuthority> grantedAuthorities =  new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(role));
		return new UsernamePasswordAuthenticationToken(username, pass,grantedAuthorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {

		return arg0.equals(UsernamePasswordAuthenticationToken.class);
	}

}
