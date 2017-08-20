package com.zcc.common.util;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Created by NCP-620 on 2017/8/17.
 */
@Component
public class TimeProvider implements Serializable {

	private static final long serialVersionUID = -3301695478208950415L;

	public Date now() {
		return new Date();
	}
}
