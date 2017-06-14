package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.entity.User;
import com.service.FeedbackService;

@Controller
public class LoginController {
	
	static Logger logger = Logger.getLogger(LoginController.class);
    
	@Autowired
	private FeedbackService feedbackService;
	
	@RequestMapping("/unauthorized")
	public String unauthorized(){
		return "/unauthorized";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model){
		model.addAttribute("user", new User());
		return "/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, HttpServletRequest request){
		Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		try {
			subject.login(token);
			return "redirect:/";
		} catch (AuthenticationException e) {
			return "redirect:/login";
		}
	}
	
	@RequestMapping("/loginOut.do")
	public String loginOut(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			subject.logout();
		}
		return "redirect:/login";
	}
	
	@RequestMapping("/")
	public ModelAndView index(){
		String curUser = (String) SecurityUtils.getSubject().getPrincipal();
		ModelAndView mav = new ModelAndView();
		mav.addObject("todoCount", feedbackService.getTodoCount(curUser));
		mav.addObject("currUser", curUser);
		mav.setViewName("index");
		return mav;
	}
	
	@RequestMapping("/index_v1")
	public String index_v1(){
		return "index_v1";
	}
	
}
