package com.classnotice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.ui.ModelMap;

@Controller
public class HomeController{

	@RequestMapping(path="/",method=RequestMethod.GET)
	public String printHello(@ModelAttribute("uid") String uid,ModelMap model){
		model.addAttribute("message","Hello,Spring MVC");
		model.addAttribute("usermessage","Welcome,"+uid);
		return "hello";
	}
}
