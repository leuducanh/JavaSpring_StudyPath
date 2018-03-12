package edu.hanoi.service.springservice;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import edu.hanoi.service.springservice.dao.model.User;

public class HNServicePermissionEvaluator implements PermissionEvaluator{
	Logger log = Logger.getLogger(HNServicePermissionEvaluator.class);
	
	@Override
	public boolean hasPermission(Authentication arg0, Object targetDomainObject, Object permission) {		
		if(targetDomainObject instanceof User){
			User user = (User) targetDomainObject;
			return user.getAge() > 5;
		}
		
		return true;
	}

	@Override
	public boolean hasPermission(Authentication arg0, Serializable arg1, String arg2, Object arg3) {
		log.info("--> " + arg2 + " " + arg3);
		return true;
	}

}
