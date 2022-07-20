package com.sns.timeline;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/timeline")
@Controller
public class TimelineController {	// timeline table이 없으므로 TimelineDAO는 필요X
	
	@RequestMapping("/timeline_view")
	public String timelineView(Model model) {
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}
