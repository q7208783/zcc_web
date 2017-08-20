package com.zcc.controller.login.response;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

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
public class AuthenticationResponse implements Serializable{
	private String token;
}
