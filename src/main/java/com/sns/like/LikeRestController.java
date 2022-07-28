package com.sns.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sns.like.bo.LikeBO;

@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	// 좋아요 / 해제
	@RequestMapping("/like/{postId}")
	public Map<String, Object> like(
			@PathVariable int postId,	// 주소에 있는 와일드 카드{postId}의 값
			HttpSession session){
		Map<String,Object> result = new HashMap<>();
		result.put("result", "success");
		
		Object userIdObj = session.getAttribute("userId");
		if(userIdObj == null) {		// 로그인 되어있지 않음
			result.put("result", "error");
			result.put("errorMessage", "로그인 후 사용 가능합니다.");
			return result;
		}
		
		// 로그인 되어있음
		int userId = (int) userIdObj;
		
		likeBO.like(userId, postId);
		
		return result;
	}
	
}
