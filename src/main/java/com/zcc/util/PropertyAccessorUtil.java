package com.zcc.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by NCP-620 on 2017/7/17.
 */
@Component
public class PropertyAccessorUtil {

	private static MessageSource messageSource;

	@Autowired
	public PropertyAccessorUtil(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public static String getMessage(String key) {
		Locale locale=LocaleContextHolder.getLocale();
		return messageSource.getMessage(key, null, locale);
	}
}
