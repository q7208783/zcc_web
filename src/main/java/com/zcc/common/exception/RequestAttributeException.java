package com.zcc.common.exception;

/**
 * Created by NCP-620 on 2017/8/9.
 */
public class RequestAttributeException extends RuntimeException{
	private int errorCode;
	private String errorMsg;
	public RequestAttributeException(int errorCode){
		this.errorCode = errorCode;
	}
	public RequestAttributeException(int errorCode, String errorMsg){
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public RequestAttributeException(int errorCode, String errorMsg, Throwable throwable){
		super(errorMsg, throwable);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
