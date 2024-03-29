package com.sns.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.follow.bo.FollowBO;
import com.sns.follow.model.Follow;
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
	
	@Autowired
	private FollowBO followBO;

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
		session.removeAttribute("profileImagePath");
		
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
		
		model.addAttribute("followers", followBO.countFollowers((int)userId));
		model.addAttribute("following", followBO.countFollowings((int)userId));
		
		model.addAttribute("viewName", "user/profile");
		return "template/layout";
	}
	
	// localhost:8080/user/profile_view
	@GetMapping("/profile_by_id_view")
	public String profileById(
			Model model,
			HttpSession session,
			@RequestParam("userId") int userId) {	// 내가 following했는지 확인할 다른 사람 userId
		
		User user = userBO.getUserById(userId);
		List<Post> postList = postBO.getPostListByUserId(userId);
		int postCount = postBO.getPostCount(userId);
		
		model.addAttribute("user",user);
		model.addAttribute("postList",postList);
		model.addAttribute("postCount",postCount);
		
		Object userIdObj = session.getAttribute("userId");	// login된 내 user id
		model.addAttribute("userId", userIdObj);
		
		if(userIdObj != null) {
			// follow 되어있는지 확인
			if(followBO.existFollow((int)userIdObj, user.getId())) {
				model.addAttribute("followed", true);
			} else {
				model.addAttribute("followed", false);
			}
		}
		
		model.addAttribute("followers", followBO.countFollowers(userId));
		model.addAttribute("following", followBO.countFollowings(userId));
		
		model.addAttribute("viewName", "user/profile");
		return "template/layout";
	}
	
	// localhost:8080/user/following_list_view
	@GetMapping("/following_list_view")
	public String followingListView(
			Model model,
			HttpSession session,
			@RequestParam("userId") int userId,
			@RequestParam("page") String page) {
		model.addAttribute("userId", userId);
		model.addAttribute("followers", followBO.countFollowers(userId));
		model.addAttribute("following", followBO.countFollowings(userId));
		model.addAttribute("page", page);
		
		List<Follow> followers = followBO.getFollowersList(userId);	// user를 팔로우
		List<Follow> following = followBO.getFollowingList(userId);	// user가 팔로잉
		
		List<User> followersList = new ArrayList<>();
		List<User> followingList = new ArrayList<>();
		
		for (Follow follow : followers) {	// 유저를 팔로우하는 유저리스트
			User user = userBO.getUserById(follow.getFollowRequestedUserId());
			followersList.add(user);
		}
		for (Follow follow : following) {	// 유저가 팔로잉하는 유저리스트
			User user = userBO.getUserById(follow.getFollowAcceptedUserId());
			followingList.add(user);
		}
		
		model.addAttribute("followersList", followersList);
		model.addAttribute("followingList", followingList);
		
		model.addAttribute("viewName", "user/followingList");
		
		return "template/layout";
	}
}
