package com.sns.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

// 화면
@RequestMapping("/user")
@Controller
public class UserController {
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostBO postBO;

	// localhost:8080/user/sign_up_view
	@RequestMapping("/sign_up_view")
	public String signUpView(Model model) {
		model.addAttribute("viewName", "user/sign_up");
		return "template/layout";
	}

	// localhost:8080/user/sign_in_view
	@RequestMapping("/sign_in_view")
	public String signInView(Model model) {
		model.addAttribute("viewName", "user/login");
		return "template/layout";
	}
	
	// localhost:8080/user/sign_out
	@RequestMapping("/sign_out")
	public String signOut(HttpSession session) {
		// 로그아웃 - 세션에 있는 모든 key-value들을 지움
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		session.removeAttribute("userName");
		
		return "redirect:/user/sign_in_view";
	}
	
	// localhost:8080/user/profile_view
	@RequestMapping("/profile_view")
	public String profile(Model model, HttpSession session) {
		Object userId = session.getAttribute("userId");
		if(userId == null) {		// 로그인이 안된 경우
			return "redirect:/user/sign_in_view";
		}
		User user = userBO.getUserById((int)userId);
		List<Post> postList = postBO.getPostListByUserId((int)userId);
		int postCount = postBO.getPostCount((int)userId);
		
		model.addAttribute("user",user);
		model.addAttribute("postList",postList);
		model.addAttribute("postCount",postCount);
		
		model.addAttribute("viewName", "user/profile");
		return "template/layout";
	}
}
