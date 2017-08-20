package com.zcc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.zcc.common.security.CustomUserService;
import com.zcc.common.security.JwtAuthenticationTokenFilter;

/**
 * Created by NCP-620 on 2017/7/12.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//	@Autowired
	//	UserDetailService service;

	@Autowired
	CustomUserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.authenticationProvider(preAuthenticatedAuthenticationProvider(service));
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers(
				"/register",
				"/auth",
				"/api",
				"/favicon.ico",
				"/**/*.html",
				"/**/*.css",
				"/**/*.js").permitAll()
			//.hasRole(ParamConstance.ROLE_NAME_USER)
			.anyRequest()
			.authenticated();

		http.formLogin()
			.loginPage("/login")
			.successForwardUrl("/showPaymentList")
			.permitAll()
			.failureUrl("/login?error=true")
			.and()
			.logout()
			.permitAll();

		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		http.csrf().disable();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**");
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter();
	}

	//	@Override
	//	public UserDetailsService userDetailsServiceBean() throws Exception {
	//		return super.userDetailsServiceBean();
	//	}

	//	@Bean
	//	public PreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter() throws Exception {
	//		PreAuthenticatedProcessingFilter filter = new PreAuthenticatedProcessingFilter();
	//
	//		filter.setCheckForPrincipalChanges(true);
	//
	//		filter.setAuthenticationManager(authenticationManager());
	//
	//		return filter;
	//	}

	//	@Bean(BeanName.AUTH_PROVIDER)
	//	public PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider(UserDetailService service) {
	//		PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
	//		provider.setPreAuthenticatedUserDetailsService(service);
	//		return provider;
	//	}

}
