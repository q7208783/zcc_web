package com.zcc.user;

import org.springframework.beans.factory.annotation.Autowired;

import com.zcc.data.UserConverter;
import com.zcc.data.dto.UserDto;

/**
 * Created by NCP-620 on 2017/8/16.
 */
public final class UserFactory {


	private static UserConverter converter;

	@Autowired
	public void setConverter(UserConverter converter){
		this.converter=converter;
	}

	private UserFactory() {
	}

	public static User createByDto(UserDto userDto){
		return new User(
			userDto.getUserId(),
			userDto.getUsername(),
			userDto.getFullname(),
			userDto.getPassword(),
			userDto.getAuthorities(),
			userDto.getLastPasswordResetDate(),
			userDto.getPayments()
		);
		//return converter.convertUserDtoToUser(userDto);
	}


	public static User createAnonymousUser(){
		return new User();
	}

	@Deprecated
	public static User createByHeader(UserHeader header){
		return new User();
	}
}
