package com.zcc.controller.payment.response;

import java.util.Map;

/**
 * Created by NCP-620 on 2017/7/14.
 */
public class PaymentResponse {
	private String returnCode;
	private Map<String, Object> responseMap;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public Map<String, Object> getResponseMap() {
		return responseMap;
	}

	public void setResponseMap(Map<String, Object> responseMap) {
		this.responseMap = responseMap;
	}
}
