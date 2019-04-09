package com.skilldistillery.larpup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.larpup.data.LarpUpDAO;

@RestController
public class MainController {

	@Autowired
	private LarpUpDAO dao;

	@RequestMapping(path = {"/" , "home.do"}, method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("recentStoryList", dao.getRecentStories(3));
		return mv;
	}
	
	
	
	/*
	 * THE BELOW METHODS JUST ALLOW US TO LINK TO TEST PAGES WHERE
	 * WE CAN WORK SEPERATELY TO TEST OUR FEATURES WE ARE ADDING
	 * AND DON'T NEED TO GRAB A PRODUCTION JSP UNTIL WE ARE ACTUALLY
	 * PREPARED TOO.
	 */
	
//	@RequestMapping(path = {"greg.do"}, method = RequestMethod.GET)
//	public ModelAndView gregsTest() {
//		ModelAndView mv = new ModelAndView("test/gregsTest");
//		return mv;
//	}
//	
//	@RequestMapping(path = {"adam.do"}, method = RequestMethod.GET)
//	public ModelAndView adamsTest() {
//		ModelAndView mv = new ModelAndView("test/adamsTest");
//		return mv;
//	}
//
//	@RequestMapping(path = {"young.do"}, method = RequestMethod.GET)
//	public ModelAndView youngsTest() {
//		ModelAndView mv = new ModelAndView("test/youngsTest");
//		return mv;
//	}
}
