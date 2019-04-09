package com.skilldistillery.larpup.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.larpup.data.EventDTO;
import com.skilldistillery.larpup.data.LarpUpDAO;
import com.skilldistillery.larpup.data.States;
import com.skilldistillery.larpup.entities.Address;
import com.skilldistillery.larpup.entities.Event;
import com.skilldistillery.larpup.entities.EventUser;
import com.skilldistillery.larpup.entities.EventUserInfo;
import com.skilldistillery.larpup.entities.User;

@RestController
public class EventController {

	@Autowired
	private LarpUpDAO dao;

	@RequestMapping(path = "displayEvent.do", method = RequestMethod.GET)
	public ModelAndView eventDisplay(int eventId, HttpSession session) {
		ModelAndView mv = new ModelAndView("eventDisplay");
		mv.addObject("event", dao.findEventById(eventId));
		
		return mv;
	}
	
	@RequestMapping(path = "eventForm.do", method = RequestMethod.GET)
	public ModelAndView updateEventForm(int eventId, HttpSession session) {
		ModelAndView mv = new ModelAndView("eventForm");
		EventDTO dto = new EventDTO();

		States states = new States();
		mv.addObject("statesList", states);
		
		mv.addObject("event", dao.findEventById(eventId));
		mv.addObject("eventDTO", dto);
		mv.addObject("action", "updateEvent.do");
		return mv;
	}

	@RequestMapping(path = "assignUserToCharacter.do", method = RequestMethod.POST)
	public ModelAndView assignUserToCharacter(int eventUserId, HttpSession session) {
		ModelAndView mv = new ModelAndView("eventDisplay");
		EventUser evtUsr = dao.findEventUserById(eventUserId);
		evtUsr.setUser((User)session.getAttribute("myUser"));
		dao.updateEventUser(evtUsr);
		dao.updateEvent(evtUsr.getEvent());
		mv.addObject("event", evtUsr.getEvent());
		return mv;
	}
	
	@RequestMapping(path = "updateEvent.do", method = RequestMethod.POST)
	public ModelAndView updateEvent(EventDTO eventDTO, HttpSession session) {
		ModelAndView mv = new ModelAndView("eventDisplay");
		Event event = dao.findEventById(eventDTO.getId());
		
		Address address = event.getAddress();
		address.setState(eventDTO.getState());
		address.setCity(eventDTO.getCity());
		address.setZipcode(eventDTO.getZipcode());
		address.setStreet(eventDTO.getStreet());
		dao.updateAddress(address);
		event.setAddress(address);
		event.setDescription(eventDTO.getDescription());
		event.setName(eventDTO.getName());
		event.setStory(dao.findStoryById(eventDTO.getStoryId()));
		event.setDate(eventDTO.getDate());
		
		if (dao.updateEvent(event)) {
			mv.addObject("event", dao.findEventById(event.getId()));
		}
		return mv;
	}
	
	@RequestMapping(path = "newEventForm.do")
	public ModelAndView addEventForm(int storyId) {
		ModelAndView mv = new ModelAndView("eventForm");
		EventDTO dto = new EventDTO();
		dto.setStoryId(storyId);
		
		States states = new States();
		mv.addObject("statesList", states);
		
		mv.addObject("event", new Event());
		mv.addObject("eventDTO", dto);
		mv.addObject("action", "addEvent.do");
		return mv;
	}

	@RequestMapping(path = "addEvent.do", method = RequestMethod.POST)
	public ModelAndView addEvent(EventDTO eventDTO) {
		ModelAndView mv = new ModelAndView("eventDisplay");
		Event event = dao.findEventById(eventDTO.getId());
		
		if( event == null) {
			event = new Event();
		}
		
		Address address = new Address();
		address.setState(eventDTO.getState());
		address.setCity(eventDTO.getCity());
		address.setZipcode(eventDTO.getZipcode());
		address.setStreet(eventDTO.getStreet());
		event.setAddress(dao.addAddress(address));
		event.setDescription(eventDTO.getDescription());
		event.setName(eventDTO.getName());
		event.setStory(dao.findStoryById(eventDTO.getStoryId()));
		event.setDate(eventDTO.getDate());
		
		mv.addObject("event", dao.addEvent(event));
		return mv;
	}
	
	@RequestMapping(path = "assignRole.do", method = RequestMethod.POST)
	public ModelAndView assignRole(int roleId, int eventId) {
		ModelAndView mv = new ModelAndView("eventDisplay");
		EventUserInfo role = dao.findEventUserInfoById(roleId);
		Event event = dao.findEventById(eventId);
		EventUser newEventUser = new EventUser();
		
		newEventUser.setEvent(event);
		newEventUser.setEventUserInfo(role);
		newEventUser.setPicture(dao.findPictureById(1));
		
		dao.addEventUser(newEventUser);
		
		mv.addObject("event", event);
		
		return mv;
	}
	
	@RequestMapping(path = "removeRole.do", method = RequestMethod.POST)
	public ModelAndView removeRole(int eventUserId, int eventId) {
		ModelAndView mv = new ModelAndView("eventDisplay");
		EventUser eventUser = dao.findEventUserById(eventUserId);
		Event event = dao.findEventById(eventId);
		
		event.removeEventUser(eventUser);
		
		dao.removeEventUser(eventUser);
		dao.updateEvent(event);
		
		mv.addObject("event", event);
		
		return mv;
	}
	
}