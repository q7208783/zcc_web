package com.zcc.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by NCP-620 on 2017/8/18.
 */
public class PasswordEncoder {

	private static BCryptPasswordEncoder encoder;

	public static String passwordEncoding(String pwd) {
		if (encoder == null)
			encoder = new BCryptPasswordEncoder();

		return encoder.encode(pwd.trim());
	}

	public static boolean passwordMatch(String pwd1,String pwd2){
		if (encoder == null)
			encoder = new BCryptPasswordEncoder();
		return encoder.matches(pwd1,pwd2);
	}
}
