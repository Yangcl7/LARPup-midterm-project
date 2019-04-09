package com.skilldistillery.larpup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.larpup.data.LarpUpDAO;
import com.skilldistillery.larpup.entities.Event;
import com.skilldistillery.larpup.entities.Story;
import com.skilldistillery.larpup.entities.User;

@RestController
public class SearchController {

	@Autowired
	private LarpUpDAO dao;

	@RequestMapping(path = "searchPage.do", method = RequestMethod.GET)
	public ModelAndView searchDisplay() {
		ModelAndView mv = new ModelAndView("search");
		return mv;
	}

	@RequestMapping(path = "searchById.do", method = RequestMethod.GET)
	public ModelAndView userDisplay(int id, String searchFor) {
		ModelAndView mv = new ModelAndView("search");
		switch(searchFor) {
		case "roles":
			mv.addObject("searchResult", dao.findEventUsersByUserId(id));
			break;
		case "storiesOwned":
			mv.addObject("searchResult", dao.findStoriesOwnedByUserId(id));
			break;
		case "storiesParticipatedIn":
			mv.addObject("searchResult", dao.findStoriesParticipatedInByUserId(id));
			break;
		}
		return mv;
	}

	@RequestMapping(path = "searchByString.do", method = RequestMethod.GET)
	public ModelAndView searchByString(String searchFor, String searchString) {
		ModelAndView mv = new ModelAndView("search");
		switch(searchFor) {
			case "users":
				List<User> users = dao.findUsersByString(searchString);
				if(users != null && users.size() > 0) {
					mv.addObject("searchResult", users);
					mv.addObject("resultClass", "user");
				}
				else {
					mv.addObject("nothingFound", "No results found for search string: " + searchString);
				}
				break;
			case "stories":
				List<Story> stories = dao.findStoriesBySearchString(searchString);
				if(stories != null && stories.size() > 0) {
					mv.addObject("searchResult", stories);
					mv.addObject("resultClass", "story");
				}
				else {
					mv.addObject("nothingFound", "No results found for search string: " + searchString);
				}
				break;
			case "events":
				List<Event> events = dao.findEventsBySearchString(searchString);
				if(events != null && events.size() > 0) {
					mv.addObject("searchResult", events);
					mv.addObject("resultClass", "event");
				}
				else {
					mv.addObject("nothingFound", "No results found for search string: " + searchString);
				}
				break;
			}
		return mv;
	}
}