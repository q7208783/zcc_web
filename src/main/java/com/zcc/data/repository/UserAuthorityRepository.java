package com.zcc.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zcc.data.dto.UserAuthorityDto;

/**
 * Created by NCP-620 on 2017/8/16.
 */
public interface UserAuthorityRepository extends JpaRepository<UserAuthorityDto, Long> {

}
