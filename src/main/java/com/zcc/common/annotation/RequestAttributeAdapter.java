package com.zcc.common.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

/**
 * Created by NCP-620 on 2017/8/8.
 */
public class RequestAttributeAdapter implements InitializingBean {
	private String name;
	private Method factoryMethod;
	private Object factory;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Method getFactoryMethod() {
		return factoryMethod;
	}

	public void setFactoryMethod(Method factoryMethod) {
		this.factoryMethod = factoryMethod;
	}

	public Object getFactory() {
		return factory;
	}

	public void setFactory(Object factory) {
		this.factory = factory;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (factory == null)
			throw new BeanCreationException("factory must be set");

		extractFactoryMethod();

		if (factoryMethod == null)
			throw new BeanCreationException("Factory class must have a @RequestAttribute annotated method");

		RequestAttribute anno = factoryMethod.getAnnotation(RequestAttribute.class);

		if (name == null)
			name = "".equals(anno.value()) ? anno.name() : anno.value();
	}

	private void extractFactoryMethod() {
		Set<Class<?>> factoryTypes = new LinkedHashSet<Class<?>>();
		factoryTypes.add(factory.getClass());
		factoryTypes.addAll(Arrays.asList(factory.getClass().getInterfaces()));

		for (Class<?> currentFactoryType : factoryTypes) {
			ReflectionUtils.doWithMethods(currentFactoryType, new ReflectionUtils.MethodCallback() {

				public void doWith(Method method) {

					Method specificMethod = ClassUtils.getMostSpecificMethod(method, factory.getClass());
					Method bridgedMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

					if (isFactoryMethod(specificMethod) && (bridgedMethod == specificMethod || !isFactoryMethod(
						bridgedMethod))) { // NOPMD

						if (factoryMethod == null) {
							factoryMethod = method;
						} else {
							throw new BeanCreationException(
								"Factory class must have only one @RequestAttribute annotated method");
						}
					}
				}

			}, USER_DECLARED_METHODS);
		}
	}

	private boolean isFactoryMethod(Method method) {
		return AnnotationUtils.findAnnotation(method, RequestAttribute.class) != null
			&& method.getReturnType() != void.class;
	}

	private static final ReflectionUtils.MethodFilter USER_DECLARED_METHODS = new ReflectionUtils.MethodFilter() {

		public boolean matches(Method method) {
			return (!method.isBridge() && method.getDeclaringClass() != Object.class);
		}
	};
}
