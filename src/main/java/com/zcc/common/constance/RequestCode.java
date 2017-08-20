package com.zcc.common.constance;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by NCP-620 on 2017/8/10.
 */
@AllArgsConstructor
public enum RequestCode {
	SUCCESS(0,"success"),
	NOT_FOUND(1000,"no record matches in db"),
	FAIL(1001,"query failed"),
	ARG_ERROR(1002, "arguments check failed");

	@Getter
	private int code;
	@Getter
	private String msg;
}
