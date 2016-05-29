package com.classnotice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import com.classnotice.beans.Account;
import com.classnotice.services.UserService;

@Controller
@SessionAttributes("uid")
public class LoginController{

	@Autowired
	private UserService userService;

	@RequestMapping(path="/login",method=RequestMethod.GET)
	public String showLogin(){
		return "login";
	}

	@RequestMapping(path="/login",method=RequestMethod.POST)
	public String processLogin(@ModelAttribute Account account,ModelMap model){
		if(userService.vertifyUserAccount(account)){ //检查账户
			//通过,跳转到主页
			model.addAttribute("uid",account.getId());
			return "redirect:/";
		}
		else{
			//不通过，转到/login
			return "redirect:/login";
		}
	}
}
