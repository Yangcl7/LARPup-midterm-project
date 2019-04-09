package com.skilldistillery.larpup.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.larpup.data.LarpUpDAO;
import com.skilldistillery.larpup.entities.User;

@RestController
public class AuthorizationController {

	@Autowired
	private LarpUpDAO dao;

	@RequestMapping(path = { "authorize.do" }, params = { "email", "password" }, method = RequestMethod.POST)
	public ModelAndView checkUser(String email, String password, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:home.do");
		User myUser = dao.findUserByEmail(email);

		try {
			if (email.contentEquals(myUser.getEmail()) && password.equals(myUser.getPassword()) && myUser.isActive()) {
				session.setAttribute("myUser", myUser);
			}else {
				mv.setViewName("loginFail");
			}
		} catch (NullPointerException e) {
			mv.setViewName("loginFail");
		}
		return mv;
	}

	@RequestMapping(path = { "logout.do" }, method = RequestMethod.GET)
	public ModelAndView userDisplay(HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:home.do");
		session.invalidate();
		return mv;
	}

}