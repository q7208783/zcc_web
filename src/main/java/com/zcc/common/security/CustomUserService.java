package com.zcc.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zcc.data.dto.UserDto;
import com.zcc.data.repository.UserRepository;
import com.zcc.user.UserFactory;

/**
 * Created by NCP-620 on 2017/8/15.
 */
@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		UserDto userDto = userRepository.findUserDtoByUsername(username);
		if (userDto == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}
		return UserFactory.createByDto(userDto);
	}
}
