package com.zcc.controller.login;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.zcc.common.constance.AuthorityName;
import com.zcc.common.constance.ParamConstance;
import com.zcc.common.security.CustomUserService;
import com.zcc.common.util.JwtTokenUtil;
import com.zcc.common.util.PasswordEncoder;
import com.zcc.controller.login.request.AuthenticationRequest;
import com.zcc.controller.login.response.AuthenticationResponse;
import com.zcc.data.dto.UserDto;
import com.zcc.data.repository.PaymentServiceImp;
import com.zcc.data.repository.UserServiceImp;
import io.swagger.annotations.ApiOperation;

/**
 * Created by NCP-620 on 2017/7/17.
 */
@Controller
public class LoginController {

	@Autowired
	UserServiceImp userServiceImp;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CustomUserService userService;

	@Autowired
	JwtTokenUtil tokenUtil;

	private static Logger logger = LogManager.getLogger(LoginController.class.getName());

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	@ApiOperation(value = "Register Controller", response = String.class)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String modifyPwd() {
		return "";
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ResponseEntity<?> authLogin(@RequestBody AuthenticationRequest request, Device device) {
		final Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails user = userService.loadUserByUsername(request.getUsername());
		String token = tokenUtil.generateToken(user,device);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(HttpServletRequest request, Model model) {

		String username = request.getParameter("username");
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		if (!password.equals(password2)) {
			model.addAttribute(ParamConstance.REQUEST_MSG, "Password is inconsistent");
			return "register";
		}

		if (!userServiceImp.existUser(username)) {

			UserDto user = new UserDto();
			user.setUsername(username);
			user.setFullname(fullname);
			user.setPassword(PasswordEncoder.passwordEncoding(password));
			user.setLastPasswordResetDate(new Date());
			user = userServiceImp.saveUserAndAuthority(user, AuthorityName.ROLE_USER_LOGIN);
			model.addAttribute(ParamConstance.REQUEST_MSG, "Register Success!");
			model.addAttribute("userId", user.getUserId());
			return "login";
		} else {
			model.addAttribute(ParamConstance.REQUEST_MSG, "Username already existed！re-input plz！");
			return "register";
		}
	}

}
