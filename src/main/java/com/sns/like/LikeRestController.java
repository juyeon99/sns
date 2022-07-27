package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeRestController {
	
	// 좋아요 / 해제
	@RequestMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId){	// 주소에 있는 와일드 카드{postId}의 값
		
		Map<String,Object> result = new HashMap<>();
		
		return result;
	}
	
}
