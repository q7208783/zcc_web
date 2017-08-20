package com.zcc.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zcc.common.annotation.RequestAttribute;
import com.zcc.common.annotation.RequestAttributeArgumentResolver;
import com.zcc.common.annotation.RequestAttributeInterceptor;

/**
 * Created by NCP-620 on 2017/8/8.
 */
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
	@Value("${zcc.interceptor.request_attribute}")
	private String[] factoriesName;
	@Autowired
	private RequestAttributeInterceptor interceptor;
	@Autowired
	private RequestAttributeArgumentResolver resolver;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(interceptor);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		super.addArgumentResolvers(argumentResolvers);
		argumentResolvers.add(resolver);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/api", "/swagger-ui.html");
	}

}
