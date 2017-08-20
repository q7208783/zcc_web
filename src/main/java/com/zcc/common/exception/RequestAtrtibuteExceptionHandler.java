package com.zcc.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcc.common.request.CommonRequest;

/**
 * Created by NCP-620 on 2017/8/9.
 */
@ControllerAdvice
public class RequestAtrtibuteExceptionHandler {

	@ExceptionHandler
	@ResponseBody
	public CommonRequest handleException(RequestAttributeException ex , HttpServletRequest request){
		return new CommonRequest();
	}
}
