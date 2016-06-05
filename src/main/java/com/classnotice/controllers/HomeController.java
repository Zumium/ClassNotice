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
import com.classnotice.beans.ReadStatus;

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
		model.addAttribute("sentCount",noticeService.countSentNotice(uid));
		model.addAttribute("isAdmin",userService.isAdmin(uid));
	}

	@RequestMapping(path="/",method=RequestMethod.GET)
	public String showUnreadList(@ModelAttribute("uid") String uid,ModelMap model){
		model.addAttribute("pageTitle","未读通知");
		model.addAttribute("pageIndex",0);
		//Assemble main list using "ListItem" bean
		List<Notice> unreadNotices=noticeService.getUnreadNotice(uid);
		model.addAttribute("listItems",produceListItems(unreadNotices,uid));

		return "noticeList";
	}

	@RequestMapping(path="/read",method=RequestMethod.GET)
	public String showReadList(@ModelAttribute("uid") String uid,ModelMap model){
		model.addAttribute("pageTitle","已读通知");
		model.addAttribute("pageIndex",1);
		//Assemble main list using "ListItem" bean
		List<Notice> readNotices=noticeService.getReadNotice(uid);
		model.addAttribute("listItems",produceListItems(readNotices,uid));

		return "noticeList";
	}

	@RequestMapping(path="/star",method=RequestMethod.GET)
	public String showStarList(@ModelAttribute("uid") String uid,ModelMap model){
		model.addAttribute("pageTitle","标星通知");
		model.addAttribute("pageIndex",2);
		//Assemble main list using "ListItem" bean
		List<Notice> starNotices=noticeService.getStarNotice(uid);
		model.addAttribute("listItems",produceListItems(starNotices,uid));

		return "noticeList";
	}

	@RequestMapping(path="/readStatus",method=RequestMethod.GET)
	public String showReadStatus(@ModelAttribute("uid") String uid,ModelMap model){
		List<Notice> sentNotices=noticeService.getSentNotice(uid);
		model.addAttribute("readStatuses",produceReadStatuses(sentNotices));
		return "readStatus";
	}

	@RequestMapping(path="/publishNotice",method=RequestMethod.GET)
	public String showPublishNoticePage(){
		return "newNotice";
	}

	//helper function
	private List<ListItem> produceListItems(List<Notice> notices,String uid){
		List<ListItem> listItems=new ArrayList<ListItem>();

		Iterator<Notice> noticeIterator=notices.iterator();
		while(noticeIterator.hasNext()){
			ListItem eachItem=new ListItem();

			Notice eachNotice=noticeIterator.next();
			eachItem.setNotice(eachNotice);
			eachItem.setSenderPortrait(userService.getPortraitUrl(eachNotice.getSender()));
			eachItem.setSenderBanner(userService.getStudent(eachNotice.getSender()));
			eachItem.setStar(noticeService.getStar(uid,eachNotice.getID()));

			listItems.add(eachItem);
		}
		return listItems;
	}

	private List<ReadStatus> produceReadStatuses(List<Notice> notices){
		List<ReadStatus> readStatuses=new ArrayList<ReadStatus>();
		Iterator<Notice> noticeIterator=notices.iterator();
	       	while(noticeIterator.hasNext()){	
			Notice notice=noticeIterator.next();

			ReadStatus newStatus=new ReadStatus();
			newStatus.setNotice(notice);
			newStatus.setReadCount(noticeService.countReadNotice(notice.getID()));
			newStatus.setNoticePath(noticeService.getNoticePath(notice.getID()));
			readStatuses.add(newStatus);
		}
		return readStatuses;
	}
}
