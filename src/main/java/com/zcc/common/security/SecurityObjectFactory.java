package com.zcc.common.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by NCP-620 on 2017/8/10.
 */
@Deprecated
public interface SecurityObjectFactory<T extends UserDetails> {
	public abstract Object getPrincipal(HttpServletRequest req);
	public abstract T getUserFromPrincipal(Object principal) throws UsernameNotFoundException;
	public abstract T getAnonymousUser(HttpServletRequest req);
}
