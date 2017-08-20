package com.zcc.common.annotation;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.core.Conventions;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.http.converter.xml.XmlAwareFormHttpMessageConverter;
import org.springframework.web.bind.annotation.support.HandlerMethodResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by NCP-620 on 2017/8/8.
 */
public class RequestAttributeInterceptor implements HandlerInterceptor, BeanFactoryAware {
	private final Log logger = LogFactory.getLog(getClass());
	private RequestAttributeAdapter[] factoriesAdapter;
	private ConfigurableBeanFactory beanFactory;
	private HttpMessageConverter[] messageConverters;

	public RequestAttributeInterceptor() {
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
		stringHttpMessageConverter.setWriteAcceptCharset(false);
		messageConverters = new HttpMessageConverter[] {new ByteArrayHttpMessageConverter(), stringHttpMessageConverter,
			new SourceHttpMessageConverter(), new XmlAwareFormHttpMessageConverter()};
	}

	public void setFactoriesAdapter(RequestAttributeAdapter[] factories) throws Exception {
		this.factoriesAdapter = factories;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		if (beanFactory instanceof ConfigurableBeanFactory) {
			this.beanFactory = (ConfigurableBeanFactory)beanFactory;
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {
		for (RequestAttributeAdapter adapter : factoriesAdapter) {
			setRequestAttribute(request, response, handler, adapter);
		}
		return true;
	}

	private void setRequestAttribute(HttpServletRequest request, HttpServletResponse response, Object handler,
		RequestAttributeAdapter adapter) throws Exception {
		if (!"".equals(adapter.getName()) && request.getAttribute(adapter.getName()) != null) {
			if (logger.isWarnEnabled()) {
				logger.warn("Request attribute already exists. Skip the attribute: " + adapter.getName());
			}
			return;
		}

		Method factoryMethod = adapter.getFactoryMethod();
		Object factory = adapter.getFactory();
		Object value = factoryMethod.invoke(factory, request);

		if (value != null) {
			String name = adapter.getName();
			if (name == null) {
				Class<?> resolvedType = GenericTypeResolver.resolveReturnType(adapter.getFactoryMethod(),
					adapter.getFactory().getClass());
				name = Conventions.getVariableNameForReturnType(adapter.getFactoryMethod(), resolvedType, value);
			}
			request.setAttribute(name, value);
		} else {
			if (logger.isDebugEnabled()) {

				logger.debug("Request attribute factory method returns null: " + adapter.getName());
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
		Exception ex) throws Exception {

	}
}
