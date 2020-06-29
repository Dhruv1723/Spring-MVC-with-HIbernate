package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bean.User;
import com.dao.UserDAO;
import com.service.UserService;



@Controller
@RequestMapping("/")
public class AddUserController {

	@Autowired
	private UserService us;
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String start() {
		return "index";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model) {

		User userForm = new User();
		model.put("userForm", userForm);

		List<String> professionList = new ArrayList<String>();
		professionList.add("Developer");
		professionList.add("Designer");
		professionList.add("IT Manager");

		model.put("professionList", professionList);

		return "registration";
	}
	
	@RequestMapping(value = "/registered", method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userForm") User user, Map<String, Object> model, Model m) {
		//UserService us = new UserServiceIMPL();
		us.addUser(user);
		return "RegistrationSuccess1";
		
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/loginreq", method=RequestMethod.POST)
	//public ModelAndView validatelogin(@FormParam("username") String username, @FormParam("password") String password) {
	public ModelAndView validatelogin(HttpServletRequest request, Model m) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		int x=us.login(username, password);
		
		ModelAndView mv = new ModelAndView();
		if(x>0) {
			mv.setViewName("loginSucess");
			mv.addObject("name", username);
		}else {
			mv.setViewName("login");
			mv.addObject("errormsg", "Please Enter Valid Login Details");
		}
		return mv;
	}
}
