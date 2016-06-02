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

	@ModelAttribute
	public void setPersonalInfo(@ModelAttribute("uid") String uid,ModelMap model){
		model.addAttribute("selfPortrait",userService.getPortraitUrl(uid));
		model.addAttribute("unreadCount",noticeService.countUnreadNotice(uid));
		model.addAttribute("readCount",noticeService.countReadNotice(uid));
		model.addAttribute("starCount",noticeService.countStarNotice(uid));
	}

	@RequestMapping(path="/",method=RequestMethod.GET)
	public String showUnreadList(@ModelAttribute("uid") String uid,ModelMap model){
		model.addAttribute("pageTitle","未读通知");
		//Assemble main list using "ListItem" bean
		List<Notice> unreadNotices=noticeService.getUnreadNotice(uid);
		model.addAttribute("listItems",produceListItems(unreadNotices));

		return "noticeList";
	}

	@RequestMapping(path="/read",method=RequestMethod.GET)
	public String showReadList(@ModelAttribute("uid") String uid,ModelMap model){
		model.addAttribute("pageTitle","已读通知");
		//Assemble main list using "ListItem" bean
		List<Notice> readNotices=noticeService.getReadNotice(uid);
		model.addAttribute("listItems",produceListItems(readNotices));

		return "noticeList";
	}

	@RequestMapping(path="/star",method=RequestMethod.GET)
	public String showStarList(@ModelAttribute("uid") String uid,ModelMap model){
		model.addAttribute("pageTitle","标星通知");
		//Assemble main list using "ListItem" bean
		List<Notice> starNotices=noticeService.getStarNotice(uid);
		model.addAttribute("listItems",produceListItems(starNotices));

		return "noticeList";
	}

	//helper function
	private List<ListItem> produceListItems(List<Notice> notices){
		List<ListItem> listItems=new ArrayList<ListItem>();

		Iterator<Notice> noticeIterator=notices.iterator();
		while(noticeIterator.hasNext()){
			ListItem eachItem=new ListItem();

			Notice eachNotice=noticeIterator.next();
			eachItem.setNotice(eachNotice);
			eachItem.setSenderPortrait(userService.getPortraitUrl(eachNotice.getSender()));
			eachItem.setSenderBanner(userService.getStudent(eachNotice.getSender()));

			listItems.add(eachItem);
		}
		return listItems;
	}
}
