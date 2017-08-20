package com.zcc.user;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by NCP-620 on 2017/8/10.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Deprecated
public class UserHeader {
	private Long userId;
	private String username;
	private String memberStatusCode;
	private String isUserLogin;
}
