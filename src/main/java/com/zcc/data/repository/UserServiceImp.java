package com.zcc.data.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcc.data.dto.AuthorityDto;
import com.zcc.common.constance.AuthorityName;
import com.zcc.data.dto.UserAuthorityDto;
import com.zcc.data.dto.UserDto;

/**
 * Created by NCP-620 on 2017/8/16.
 */
@Service
public class UserServiceImp {
	@Autowired
	AuthorityRepository authorityRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserAuthorityRepository userAuthorityRepository;

	public void saveUser(UserDto userDto) {
		this.saveUserAndAuthority(userDto, null);
	}

	public UserDto findUserById(Long id) {
		return userRepository.findOne(id);
	}

	public UserDto findUserByUsername(String username) {
		return userRepository.findUserDtoByUsername(username);
	}

	public UserDto saveUserAndAuthority(UserDto userDto, AuthorityName authorityName) {
		if (!existUser(userDto.getUsername()))
			userDto = userRepository.save(userDto);
		Long userId = userDto.getUserId();

		AuthorityDto authorityDto = new AuthorityDto();
		authorityDto.setName(authorityName);
		authorityDto = authorityRepository.save(authorityDto);
		Long authorityId = authorityDto.getAuthorityId();

		UserAuthorityDto userAuthorityDto = new UserAuthorityDto();
		userAuthorityDto.setUserId(userId);
		userAuthorityDto.setAuthorityId(authorityId);
		userAuthorityRepository.save(userAuthorityDto);

		return userDto;
	}

	public UserDto deleteUserByUserId(Long id){
		UserDto user = findUserById(id);
		if(user!=null)
			userRepository.delete(user);
		return user;
	}

	public UserDto alterPassword(String oldPwd,String newPwd,Long id){
		UserDto user = findUserById(id);

		return user;
		//userRepository.
	}

	public boolean existUser(String username) {
		return userRepository.findUserDtoByUsername(username) == null ? false : true;
	}
}
