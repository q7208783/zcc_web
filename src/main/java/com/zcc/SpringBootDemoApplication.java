package com.zcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zcc.common.annotation.RequestAttributeAdapter;
import com.zcc.common.annotation.RequestAttributeInterceptor;

@SpringBootApplication
public class SpringBootDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
