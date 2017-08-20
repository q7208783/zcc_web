package com.zcc.common.request;

import com.zcc.common.constance.RequestCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by NCP-620 on 2017/8/9.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommonRequest {
	private int code;
	private String msg;
	private Object extraMsg;
	private Object data;

	public CommonRequest(RequestCode code, String extraSsg, Object data) {
		this(code.getCode(), code.getMsg(), extraSsg, data);
	}
}
