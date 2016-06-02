package com.classnotice;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.ModelMap;

import com.classnotice.services.NoticeService;
import com.classnotice.services.UserService;
import com.classnotice.db.entities.Notice;
import com.classnotice.db.entities.Student;
import com.classnotice.beans.ListItem;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
@SessionAttributes({"uid"})
public class PageController{

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;

	@RequestMapping(path="/notice/{noticeId}",method=RequestMethod.GET)
	public String showNotice(@ModelAttribute("uid") String uid,@PathVariable int noticeId,ModelMap model){
		model.addAttribute("pageTitle","通知阅读");
		model.addAttribute("selfPortrait",userService.getPortraitUrl(uid));
		model.addAttribute("unreadCount",noticeService.countUnreadNotice(uid));
		model.addAttribute("readCount",noticeService.countReadNotice(uid));
		model.addAttribute("starCount",noticeService.countStarNotice(uid));
		//Find the notice and set it up,then put it into model
		Notice notice=noticeService.getNotice(noticeId);
		ListItem item=new ListItem();
		item.setNotice(notice);
		item.setSenderBanner(userService.getStudent(notice.getSender()));
		item.setSenderPortrait(userService.getPortraitUrl(notice.getSender()));
		model.addAttribute("noticeItem",item);

		return "readPage";
	}
}
