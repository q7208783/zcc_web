package com.zcc.controller.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by NCP-620 on 2017/7/18.
 */
@Controller
public class HelloController {
	@RequestMapping("/")
	public String index(){
		return "index";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}
}
