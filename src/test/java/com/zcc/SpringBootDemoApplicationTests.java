package com.zcc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.zcc.data.repository.PaymentRepository;
import com.zcc.util.PropertyAccessorUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

	MockMvc mock;

	@Autowired
	private PaymentRepository pr;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Test
	public void contextLoads() {

	}

	@Before
	public void init(){
		mock= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addToDB() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time = null;
//		pr.save(new PaymentDto(new Date(), "$5", "eat noodle", "eating"));
//		pr.save(new PaymentDto(new Date(), "$8", "eat lunch", "eating"));
//		pr.save(new PaymentDto(new Date(), "$3", "taxi", "transfer"));

		Assert.assertEquals(3, pr.findAll().size());
		pr.delete(2L);
		Assert.assertEquals(2, pr.findAll().size());

	}

	@Test
	public void utilTest() {
		Assert.assertEquals("这是一个SpringBoot的Demo",PropertyAccessorUtil.getMessage("com.bootdeomo.title"));
	}


	private Long id;
	private String username;
	private String fullname;
	private String password;

	@Test
	public void createUserTest() throws Exception{
		mock.perform(post("/register")
			.param("username","Johnson")
			.param("fullname","Johnson Zhang")
			.param("password","123456"))
		.andExpect(model().attributeExists("user"));
	}

}
