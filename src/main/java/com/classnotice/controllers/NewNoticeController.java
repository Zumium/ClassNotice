package com.classnotice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;

import com.classnotice.services.NoticeService;
import com.classnotice.services.UserService;
import com.classnotice.beans.NewNotice;
import com.classnotice.beans.NewNoticeForm;
import com.classnotice.exceptions.NoPermissionException;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@SessionAttributes({"uid"})
public class NewNoticeController{
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private UserService userService;

	@RequestMapping(path="/notice",method=RequestMethod.POST)
	public String publishNewNotice(@ModelAttribute NewNoticeForm newNoticeForm,@ModelAttribute("uid") String uid){
		if(!userService.isAdmin(uid)) throw new NoPermissionException();

		NewNotice newNotice=new NewNotice();
		newNotice.setTitle(newNoticeForm.getTitle());
		newNotice.setContent(newNoticeForm.getContent());
		newNotice.setSender(uid);
		newNotice.setReceivers(userService.getReceiversIdArray(newNoticeForm.getReceiversGroup()));
		newNotice.setPublishTime(new Timestamp(new Date().getTime()));
		noticeService.publishNewNotice(newNotice);
		return "successPublish";
	}
}
