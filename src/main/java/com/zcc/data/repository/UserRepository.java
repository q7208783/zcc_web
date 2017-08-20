package com.zcc.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zcc.data.dto.UserDto;

/**
 * Created by NCP-620 on 2017/7/12.
 */
public interface UserRepository extends JpaRepository<UserDto, Long> {
	UserDto findUserDtoByUsername(String username);

    @Modifying
	@Query("update UserDto u SET u.password = ?2 where u.userId=?3 and u.password=?1")
	boolean alterUserPassword(
		@Param(value = "oldPwd") String oldPwd,
		@Param(value = "newPwd") String newPwd,
		@Param(value = "id") Long id);
}
