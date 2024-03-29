package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String,Object> create(
			@RequestParam("content") String content,
			// @RequestParam(value="file", required=false) MultipartFile file,
			@RequestParam("file") MultipartFile file,
			HttpSession session){
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		// 글쓴이 정보를 세션에서 꺼낸다.
		Object userIdObj = session.getAttribute("userId");
		if(userIdObj == null) {		// 로그인 되어있지 않음
			result.put("result", "error");
			result.put("errorMessage", "로그인 후 사용 가능합니다.");
			return result;
		}
		
		// 로그인 되어있음
		int userId = (int) userIdObj;
		String userLoginId = (String)session.getAttribute("userLoginId");
		
		// 글쓰기 db insert
		postBO.addPost(userId, userLoginId, content, file);
		
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String,Object> delete(
			@RequestParam("postId") int postId,
			HttpSession session){
		
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		
		Object userIdObj = session.getAttribute("userId");
		if(userIdObj == null) {		// 로그인 되어있지 않음
			result.put("result", "error");
			result.put("errorMessage", "로그인 후 사용 가능합니다.");
			return result;
		}
		int userId = (int) userIdObj;
		
		postBO.deletePostByPostIdAndUserId(postId, userId);
		
		return result;
	}
	
}
