package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.model.CardView;

@RequestMapping("/timeline")
@Controller
public class TimelineController {	// timeline table이 없으므로 TimelineDAO는 필요X
	
//	@Autowired
//	private PostBO postBO;
	
	@Autowired
	private TimelineBO timelineBO;
	
	// http://localhost:8080/timeline/timeline_view
	@RequestMapping("/timeline_view")
	public String timelineView(Model model, HttpSession session) {
		Object userId = session.getAttribute("userId");
		model.addAttribute("userId", userId);
		
//		List<Post> postList = postBO.getPostList();
//		model.addAttribute("postList", postList);
		List<CardView> cardList = timelineBO.generateCardList((Integer) userId);	// CardView = Post + Comment + User + Like
		model.addAttribute("cardList", cardList);
		
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}
