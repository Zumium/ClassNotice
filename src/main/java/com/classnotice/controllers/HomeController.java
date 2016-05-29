package com.classnotice;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.ModelMap;

import com.classnotice.db.NoticeDAO;
import com.classnotice.db.entities.Notice;

@Controller
public class HomeController{

	@Autowired
	private NoticeDAO noticeDao;

	@RequestMapping(path="/",method=RequestMethod.GET)
	public String printHello(@ModelAttribute("uid") String uid,ModelMap model){
		Notice notice=noticeDao.Query(1);
		model.addAttribute("title",notice.getTitle());
		model.addAttribute("content",notice.getContent());
		model.addAttribute("sender",notice.getSender());

		model.addAttribute("message","Hello,Spring MVC");
		model.addAttribute("usermessage","Welcome,"+uid);
		return "hello";
	}
}
