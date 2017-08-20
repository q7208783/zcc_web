package com.zcc.user;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.zcc.common.constance.BeanName;
import com.zcc.common.security.SecurityObjectFactory;

/**
 * Created by NCP-620 on 2017/8/10.
 */
@Component
@Deprecated
public class UserDetailService implements AuthenticationUserDetailsService, InitializingBean, ApplicationContextAware {
	ApplicationContext context;

	@Autowired
	@Qualifier(BeanName.USER_FACTORY)
	private SecurityObjectFactory<? extends UserDetails> userFactory;
	@Override
	public void afterPropertiesSet() throws Exception {

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context=applicationContext;
	}

	@Override
	public UserDetails loadUserDetails(Authentication authentication) throws UsernameNotFoundException {
		Object principal = authentication.getPrincipal();
		return userFactory.getUserFromPrincipal(principal);
	}
}
