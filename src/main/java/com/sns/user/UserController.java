package com.sns.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// 화면
@RequestMapping("/user")
@Controller
public class UserController {

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
		model.addAttribute("userId", userId);
		
		model.addAttribute("viewName", "user/profile");
		return "template/layout";
	}
}
