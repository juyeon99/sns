package com.sns.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			@RequestParam("password") String password){
		// db select
		String encryptedPassword = EncryptUtils.md5(password);
		boolean existsInfo = userBO.searchUserById(loginId,encryptedPassword);
		Map<String,Object> result = new HashMap<>();
		result.put("result", existsInfo);
		return result;
	}
	
}