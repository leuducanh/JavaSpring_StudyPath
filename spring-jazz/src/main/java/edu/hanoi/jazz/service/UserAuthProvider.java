package edu.hanoi.jazz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import edu.hanoi.jazz.dao.UserDAO;

@Component("userAuthProvider")
public class UserAuthProvider implements AuthenticationProvider {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName().toString();
		edu.hanoi.jazz.dao.model.User user = userDAO.getUser(username);
		if(user == null) return null;
		if(!user.getPassword().equals(authentication.getCredentials().toString()))return null;
		
		return successful(username, authentication.getCredentials().toString());
	}
	
	private UsernamePasswordAuthenticationToken successful(String username,String password){
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		return new UsernamePasswordAuthenticationToken(username, password,grantedAuthorities);
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
