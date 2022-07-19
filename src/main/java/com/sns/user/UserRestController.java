package com.sns.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}