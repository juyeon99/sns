package com.sns.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@RequestMapping("/user")
@RestController
public class UserRestController {
	
	@Autowired
	private UserBO userBO;
	
	@RequestMapping("/user_list")
	public List<User> userList(){
		return userBO.getUserList();
	}
	
	@RequestMapping("/is_duplicated_id")
	public Map<String,Object> isDuplicatedId(
			@RequestParam("loginId") String loginId){
		// db select
		boolean exists = userBO.existingID(loginId);
		Map<String,Object> result = new HashMap<>();
		result.put("result", exists);
		return result;
	}
	
	@RequestMapping("/sign_up")
	public Map<String,Object> signUp(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			@RequestParam("name") String name,
			@RequestParam("email") String email){
		String encryptedPassword = EncryptUtils.md5(password);
		
		userBO.addUser(loginId,encryptedPassword,name,email);
		
		Map<String,Object> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
	
	@RequestMapping("/sign_in")
	public Map<String,Object> signIn(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password,
			HttpServletRequest req){
		String encryptedPassword = EncryptUtils.md5(password);
		User user = userBO.getUserByLoginId(loginId,encryptedPassword);
		
		Map<String,Object> result = new HashMap<>();
		if(user != null) {
			// session - 로그인이 성공하면 로그인 상태 유지를 위해 세션에 사용자에 대한 필요한 정보를 담는다.
			HttpSession session = req.getSession();
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			
			result.put("result", "success");
		} else {
			// 실패하면 실패 응답
			result.put("errorMessage", "존재하지 않는 사용자입니다.");
		}
		
		return result;
	}
	
}