package com.zcc.config;

import java.util.Locale;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.zcc.common.annotation.RequestAttributeAdapter;
import com.zcc.common.annotation.RequestAttributeInterceptor;

/**
 * Created by NCP-620 on 2017/8/9.
 */
@Configuration
public class BeanManagerConfig {

	@Value("${zcc.interceptor.request_attribute}")
	private String[] factoriesName;

	@Autowired
	private ApplicationContext context;

	@Bean
	public RequestAttributeInterceptor factories() throws Exception {
		RequestAttributeAdapter[] factories = new RequestAttributeAdapter[factoriesName.length];
		RequestAttributeInterceptor interceptor = new RequestAttributeInterceptor();
		for (int i = 0; i < factories.length; i++) {
			Class<?> clazz = Class.forName(factoriesName[i]);
			Object factory = context.getBean(clazz);
			RequestAttributeAdapter adapter = new RequestAttributeAdapter();
			adapter.setFactory(factory);
			adapter.afterPropertiesSet();
			factories[i] = adapter;
		}
		interceptor.setFactoriesAdapter(factories);
		return interceptor;
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		//设置默认区域,
		slr.setDefaultLocale(Locale.CHINA);
		return slr;
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource
			= new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(3600);
		return messageSource;
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}
}
