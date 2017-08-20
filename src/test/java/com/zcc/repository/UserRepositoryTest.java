package com.zcc.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zcc.data.repository.UserServiceImp;

/**
 * Created by NCP-620 on 2017/8/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
	@Autowired
	UserServiceImp serviceImp;


}
