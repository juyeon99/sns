package com.sns.follow;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.follow.bo.FollowBO;

@RequestMapping("/follow")
@RestController
public class FollowRestController {
	
	@Autowired
	private FollowBO followBO;
	
	@PostMapping("/create")
	public Map<String,Object> create(
			@RequestParam("acceptedUserId") int acceptedUserId,
			HttpSession session){
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		Object userIdObj = session.getAttribute("userId");
		if(userIdObj == null) {		// 로그인 되어있지 않음
			result.put("result", "error");
			result.put("errorMessage", "로그인 후 사용 가능합니다.");
			return result;
		}
		
		// 로그인 되어있음
		int userId = (int) userIdObj;
		
		// follow db insert
		followBO.addFollow(userId, acceptedUserId);
		
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String,Object> delete(
			@RequestParam("acceptedUserId") int acceptedUserId,
			HttpSession session){
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		Object userIdObj = session.getAttribute("userId");
		if(userIdObj == null) {		// 로그인 되어있지 않음
			result.put("result", "error");
			result.put("errorMessage", "로그인 후 사용 가능합니다.");
			return result;
		}
		
		// 로그인 되어있음
		int userId = (int) userIdObj;
		
		followBO.deleteFollow(userId, acceptedUserId);
		
		return result;
	}
}
