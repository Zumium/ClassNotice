package com.classnotice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController{

	@RequestMapping(path="/logout",method=RequestMethod.GET)
	public String processLogout(HttpSession session){
		session.invalidate();
		return "redirect:/";
	}
}
