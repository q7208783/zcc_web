package com.zcc.common.annotation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.zcc.user.UserFactory;

/**
 * Created by NCP-620 on 2017/8/8.
 */
@Component("userFactory")
public class UserRequestAttributeFactory implements InitializingBean {

	@RequestAttribute("user")
	public UserDetails getObject(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (isAnonymous(auth)) {
			return UserFactory.createAnonymousUser();
		}
		return (UserDetails) auth.getPrincipal();
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	private boolean isAnonymous(Authentication auth){
		return auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass());
	}
}
