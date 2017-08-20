package com.zcc.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zcc.data.dto.AuthorityDto;

/**
 * Created by NCP-620 on 2017/8/15.
 */
public interface AuthorityRepository extends JpaRepository<AuthorityDto, Long> {

}
