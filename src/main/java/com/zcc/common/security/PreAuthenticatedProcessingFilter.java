package com.zcc.common.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

/**
 * Created by NCP-620 on 2017/8/10.
 */
@Deprecated
public class PreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter implements ApplicationContextAware {
	private ApplicationContext context;
	@Autowired
	private SecurityObjectFactory<? extends UserDetails> userFactory;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context=applicationContext;
	}

	@Override
	protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
		return userFactory.getPrincipal(httpServletRequest);
	}

	@Override
	protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
		return "";
	}


}
