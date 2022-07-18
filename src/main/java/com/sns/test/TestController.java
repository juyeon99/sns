package com.sns.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	// 1. String return 확인
	@ResponseBody
	@RequestMapping("/test/1")
	public String test1() {
		return "Hello world!";
	}
	
	// 2. Map return 확인
	@ResponseBody
	@RequestMapping("/test/2")
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("a", 1234);
		map.put("b", 1234);
		map.put("c", 1234);
		
		return map;
	}
	
	// 3. jsp 연동 확인
	@RequestMapping("/test/3")
	public String test3() {
		return "test/test";
	}
}
