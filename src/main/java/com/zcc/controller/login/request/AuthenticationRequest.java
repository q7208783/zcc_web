package com.zcc.controller.login.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by NCP-620 on 2017/8/18.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements Serializable{
	private String username;
	private String password;
}
