package com.classnotice;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
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
public class HomeController{

	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;

	@RequestMapping(path="/",method=RequestMethod.GET)
	public String showList(@ModelAttribute("uid") String uid,ModelMap model){

	/*	model.addAttribute("portraitUrl",userService.getPortraitUrl(uid));

		int unreadNoticeCount=noticeService.countUnreadNotice(uid);
		model.addAttribute("unreadCount",unreadNoticeCount);

		int totalNoticeCount=noticeService.countTotalNotice(uid);
		model.addAttribute("totalCount",unreadNoticeCount);

		List<Notice> unreadNotice=noticeService.getUnreadNotice(uid);
		model.addAttribute("unreadNotice",unreadNotice);
	*/
		model.addAttribute("pageTitle","未读通知");
		model.addAttribute("selfPortrait",userService.getPortraitUrl(uid));
		model.addAttribute("unreadCount",noticeService.countUnreadNotice(uid));
		model.addAttribute("readCount",noticeService.countReadNotice(uid));
		model.addAttribute("starCount",noticeService.countStarNotice(uid));
		//Assemble main list using "ListItem" bean
		List<ListItem> listItems=new ArrayList<ListItem>();

		List<Notice> unreadNotices=noticeService.getUnreadNotice(uid);
		Iterator<Notice> noticeIterator=unreadNotices.iterator();
		while(noticeIterator.hasNext()){
			ListItem eachItem=new ListItem();

			Notice eachNotice=noticeIterator.next();
			eachItem.setNotice(eachNotice);
			eachItem.setSenderPortrait(userService.getPortraitUrl(eachNotice.getSender()));
			eachItem.setSenderBanner(userService.getStudent(eachNotice.getSender()));

			listItems.add(eachItem);
		}
		model.addAttribute("listItems",listItems);

		return "noticeList";
	}
}
