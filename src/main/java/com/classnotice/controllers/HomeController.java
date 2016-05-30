package com.classnotice;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.ModelMap;

import com.classnotice.services.NoticeService;

@Controller
public class HomeController{

	@Autowired
	private NoticeService noticeService;

	@RequestMapping(path="/",method=RequestMethod.GET)
	public String printHello(@ModelAttribute("uid") String uid,ModelMap model){
		model.addAttribute("message","Hello,Spring MVC");
		model.addAttribute("usermessage","Welcome,"+uid);

		int unreadNoticeCount=noticeService.CountUnreadNotice(uid);
		model.addAttribute("unreadCount",unreadNoticeCount);

		return "hello";
	}
}
