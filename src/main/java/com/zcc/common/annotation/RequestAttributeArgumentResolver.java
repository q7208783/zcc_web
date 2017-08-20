package com.zcc.common.annotation;

import org.springframework.core.Conventions;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Created by NCP-620 on 2017/8/8.
 */
@Component
public class RequestAttributeArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		RequestAttribute anno = parameter.getParameterAnnotation(RequestAttribute.class);
		return anno != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		RequestAttribute anno = parameter.getParameterAnnotation(RequestAttribute.class);
		String name = "".equals(anno.name()) ? anno.value() : anno.name();
		if (name.equals("")) {
			name = Conventions.getVariableNameForParameter(parameter);
		}

		return webRequest.getAttribute(name, NativeWebRequest.SCOPE_REQUEST);
	}
}
