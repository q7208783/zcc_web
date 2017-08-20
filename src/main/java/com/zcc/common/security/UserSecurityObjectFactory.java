package com.zcc.common.security;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.zcc.common.constance.BeanName;
import com.zcc.user.User;
import com.zcc.user.UserFactory;
import com.zcc.user.UserHeader;

/**
 * Created by NCP-620 on 2017/8/10.
 */
@Component(BeanName.USER_FACTORY)
@Deprecated
public class UserSecurityObjectFactory implements SecurityObjectFactory<User> {

	private final Log logger = LogFactory.getLog(this.getClass());

	@Override
	public UserHeader getPrincipal(HttpServletRequest request) {
		this.logger.debug("===getPrincipal===");
		String header=request.getHeader("");
		String clientIp = request.getRemoteAddr();
		String currentCookieName;
		String authTokenValue;
		return new UserHeader();
	}

	@Override
	public User getUserFromPrincipal(Object principal) throws UsernameNotFoundException {
		UserHeader header = UserHeader.class.cast(principal);
		return UserFactory.createByHeader(header);
	}

	@Override
	public User getAnonymousUser(HttpServletRequest request) {
		return new User();
	}
}
