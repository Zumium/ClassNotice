package com.classnotice;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.ModelMap;

import com.classnotice.services.NoticeService;
import com.classnotice.db.entities.Notice;

import java.util.List;

@Controller
@SessionAttributes({"uid"})
public class HomeController{

	@Autowired
	private NoticeService noticeService;

	@RequestMapping(path="/",method=RequestMethod.GET)
	public String printHello(@ModelAttribute("uid") String uid,ModelMap model){
		model.addAttribute("message","Hello,Spring MVC");
		model.addAttribute("usermessage","Welcome,"+uid);

		int unreadNoticeCount=noticeService.countUnreadNotice(uid);
		model.addAttribute("unreadCount",unreadNoticeCount);

		int totalNoticeCount=noticeService.countTotalNotice(uid);
		model.addAttribute("totalCount",unreadNoticeCount);

		List<Notice> unreadNotice=noticeService.getUnreadNotice(uid);
		model.addAttribute("unreadNotice",unreadNotice);

		return "hello";
	}
}
