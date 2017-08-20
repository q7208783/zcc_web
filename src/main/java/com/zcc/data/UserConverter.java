package com.zcc.data;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zcc.data.dto.UserDto;
import com.zcc.user.User;

/**
 * Created by NCP-620 on 2017/8/17.
 */
@Component
public class UserConverter {
	@Autowired
	ModelMapper modelMapper;

	public User convertUserDtoToUser(@NotNull UserDto user) {
		return modelMapper.map(user, User.class);
	}

	public UserDto convertUserToUserDto(@NotNull User user){
		UserDto userDto = modelMapper.map(user,UserDto.class);
		return userDto;
	}
}
