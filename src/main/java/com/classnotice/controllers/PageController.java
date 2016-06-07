package com.classnotice;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.ModelMap;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.classnotice.services.NoticeService;
import com.classnotice.services.UserService;
import com.classnotice.db.entities.Notice;
import com.classnotice.db.entities.Student;
import com.classnotice.beans.ListItem;
import com.classnotice.beans.StarStatus;

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

	@RequestMapping(path="/ajax/notice/{noticeId}/star",method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity ajaxSetNoticeStatus(@ModelAttribute("uid") String uid,@PathVariable int noticeId,@RequestBody StarStatus starStatus){
		noticeService.setStar(uid,noticeId,starStatus.getStar());
		return new ResponseEntity(HttpStatus.valueOf(200));
	}

	@RequestMapping(path="/ajax/starcount",method=RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String ajaxGetStarCount(@ModelAttribute("uid") String uid){
		int count=noticeService.countStarNotice(uid);
		return "{\"num\":"+count+"}";
	}
}
