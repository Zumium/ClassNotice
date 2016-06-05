package com.classnotice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;

import com.classnotice.services.NoticeService;
import com.classnotice.services.UserService;
import com.classnotice.beans.NewNotice;
import com.classnotice.beans.NewNoticeForm;

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
	public ResponseEntity<String> publishNewNotice(@ModelAttribute NewNoticeForm newNoticeForm,@ModelAttribute("uid") String uid){
		HttpHeaders responseHeaders=new HttpHeaders();
		responseHeaders.add("Content-Type", "text/plain; charset=UTF-8");
		if(!userService.isAdmin(uid)){
			return new ResponseEntity<String>("普通同学不能发布通知",responseHeaders,HttpStatus.FORBIDDEN);
		}
		NewNotice newNotice=new NewNotice();
		newNotice.setTitle(newNoticeForm.getTitle());
		newNotice.setContent(newNoticeForm.getContent());
		newNotice.setSender(uid);
		newNotice.setReceivers(userService.getReceiversIdArray(newNoticeForm.getReceiversGroup()));
		newNotice.setPublishTime(new Timestamp(new Date().getTime()));
		noticeService.publishNewNotice(newNotice);
		return new ResponseEntity<String>("发布成功",responseHeaders,HttpStatus.OK);
	}
}
