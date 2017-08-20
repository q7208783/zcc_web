package com.zcc.common.exception;

/**
 * Created by NCP-620 on 2017/8/16.
 */
public class CommonException extends RuntimeException{
	private int errorCode;
	private String errorMsg;
	public CommonException(int errorCode){
		this.errorCode = errorCode;
	}
	public CommonException(int errorCode, String errorMsg){
		super(errorMsg);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	public CommonException(int errorCode, String errorMsg, Throwable throwable){
		super(errorMsg, throwable);
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
