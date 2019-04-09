package com.skilldistillery.larpup.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.larpup.data.EventUserInfoDTO;
import com.skilldistillery.larpup.data.LarpUpDAO;
import com.skilldistillery.larpup.data.States;
import com.skilldistillery.larpup.data.StoryDTO;
import com.skilldistillery.larpup.entities.Address;
import com.skilldistillery.larpup.entities.EventUserInfo;
import com.skilldistillery.larpup.entities.Genre;
import com.skilldistillery.larpup.entities.Picture;
import com.skilldistillery.larpup.entities.Story;
import com.skilldistillery.larpup.entities.User;

@RestController
public class StoryController {

	@Autowired
	private LarpUpDAO dao;

	@RequestMapping(path = { "displayStory.do" }, method = RequestMethod.GET)
	public ModelAndView displayStory(int storyId, HttpSession session) {
		Story myStory = dao.findStoryById(storyId);
		
		ModelAndView mv = new ModelAndView("storyDisplay");
		mv.addObject("story", myStory);
		return mv;
	}

	@RequestMapping(path = { "modifyStory.do" }, method = RequestMethod.GET)
	public ModelAndView modifyStoryForm(int storyId) {
		Story myStory = dao.findStoryById(storyId);
		
		StoryDTO dto = new StoryDTO();
		
				
		ModelAndView mv = new ModelAndView("storyForm");
		mv.addObject("story", myStory);
		mv.addObject("inputDTO", dto);
		mv.addObject("genres", dao.findGenresByName(""));
		mv.addObject("action", "modifyStory.do");
		
		States states = new States();
		mv.addObject("statesList", states);
		return mv;
	}

	@RequestMapping(path = {"modifyStory.do"}, method = RequestMethod.POST)
	public ModelAndView modifyStory(StoryDTO inputDTO) {
		ModelAndView mv = new ModelAndView();
		
		Story managedStory = dao.findStoryById(inputDTO.getStoryId());
		
		int storyIdToUpdate = managedStory.getId();
		
		
		managedStory.setName(inputDTO.getStoryName());
		managedStory.setDescription(inputDTO.getStoryDescription());
		managedStory.setId(inputDTO.getStoryId());
		
		Address address = new Address();
		address.setCity(inputDTO.getAddressCity());
		address.setState(inputDTO.getAddressState());
		address.setZipcode(inputDTO.getAddressZipcode());
		address = dao.addAddress(address);
		managedStory.setAddress(address);
		
		Genre updateGenre = null;
		if(inputDTO.getCustomGenreName() != null && inputDTO.getCustomGenreName().length() > 0) {
			updateGenre = new Genre();
			updateGenre.setName(inputDTO.getCustomGenreName());
			updateGenre.setPicture(dao.findPictureById(4));
			dao.addGenre(updateGenre);
		}
		else {
			updateGenre = dao.findGenresByName(inputDTO.getGenreName()).get(0);
		}
		managedStory.setGenre(updateGenre);
		
		if (dao.updateStory(managedStory)) {
			Story updatedStory = dao.findStoryById(storyIdToUpdate);
			
			mv.setViewName("storyDisplay");
			mv.addObject("story", updatedStory);
		} else {
			mv.setViewName("storyForm");
			mv.addObject("action", "modifyStory.do");
			mv.addObject("story", dao.findStoryById(storyIdToUpdate));
		}
		return mv;
	}

	@RequestMapping(path = { "addStory.do" }, method = RequestMethod.GET)
	public ModelAndView addStoryForm() {
				
		StoryDTO dto = new StoryDTO();
		
		ModelAndView mv = new ModelAndView("storyForm");
		mv.addObject("inputDTO", dto);
		mv.addObject("action", "addStory.do");
		mv.addObject("genres", dao.findGenresByName(""));
		
		States states = new States();
		mv.addObject("statesList", states);
		return mv;
	}
	
	@RequestMapping(path = { "addStory.do" }, method = RequestMethod.POST)
	public ModelAndView addStory(StoryDTO inputDTO, HttpSession session) {
		
		Story newStory = new Story();
		Address newAddress = new Address();
		
		Genre genre = null;
		if(inputDTO.getCustomGenreName() != null && inputDTO.getCustomGenreName().length() > 0) {
			genre = new Genre();
			genre.setName(inputDTO.getCustomGenreName());
			genre.setPicture(dao.findPictureById(4));
			dao.addGenre(genre);
		}
		else {
			genre = dao.findGenresByName(inputDTO.getGenreName()).get(0);
		}
		
		newAddress.setCity(inputDTO.getAddressCity());
		newAddress.setState(inputDTO.getAddressState());
		newAddress.setZipcode(inputDTO.getAddressZipcode());
		newAddress = dao.addAddress(newAddress);
		
		newStory.setGenre(genre);
		newStory.setAddress(newAddress);
		newStory.setName(inputDTO.getStoryName());
		newStory.setDescription(inputDTO.getStoryDescription());
		newStory.setUser((User)session.getAttribute("myUser"));
		
		Story managedStory = dao.addStory(newStory);
		
		ModelAndView mv = new ModelAndView("storyDisplay");
		mv.addObject("story", managedStory);
		return mv;
	}
	
	@RequestMapping(path = { "addRole.do" }, method = RequestMethod.GET)
	public ModelAndView addRoleForm(int storyId) {
		
		Story myStory = dao.findStoryById(storyId);
		ModelAndView mv = new ModelAndView("roleForm");
		mv.addObject("story", myStory);
		mv.addObject("inputDTO", new EventUserInfoDTO());
		mv.addObject("action", "addRole.do");
		return mv;
	}
	
	@RequestMapping(path = { "addRole.do" }, method = RequestMethod.POST)
	public ModelAndView addRole(EventUserInfoDTO inputDTO) {
		
		EventUserInfo role = new EventUserInfo();
		
		role.setName(inputDTO.getName());
		role.setDescription(inputDTO.getDescription());
		role.setStory(dao.findStoryById(inputDTO.getStoryId()));
		
		role = dao.addEventUserInfo(role);
		
		ModelAndView mv = new ModelAndView("storyDisplay");
		mv.addObject("story", role.getStory());
		return mv;
	}
	
	@RequestMapping(path = { "modifyRole.do" }, method = RequestMethod.GET)
	public ModelAndView modifyRoleForm(int roleId) {
		
		EventUserInfo myRole = dao.findEventUserInfoById(roleId);
		
		ModelAndView mv = new ModelAndView("roleForm");
		mv.addObject("inputDTO", new EventUserInfoDTO());
		mv.addObject("role", myRole);
		mv.addObject("story", myRole.getStory());
		mv.addObject("action", "modifyRole.do");
		return mv;
	}
	
	@RequestMapping(path = { "modifyRole.do" }, method = RequestMethod.POST)
	public ModelAndView modifyRole(EventUserInfoDTO inputDTO) {
		ModelAndView mv = new ModelAndView();
		
		EventUserInfo managedRole = dao.findEventUserInfoById(inputDTO.getId());
		managedRole.setName(inputDTO.getName());
		managedRole.setDescription(inputDTO.getDescription());
		managedRole.setStory(dao.findStoryById(inputDTO.getStoryId()));
		
		if (dao.updateEventUserInfo(managedRole)) {
			mv.setViewName("storyDisplay");
			mv.addObject("story", managedRole.getStory());
		} else {
			mv.setViewName("roleForm");
			mv.addObject("role", managedRole);
			mv.addObject("story", managedRole.getStory());
			mv.addObject("action", "modifyRole.do");
		}
		return mv;
	}

	@RequestMapping(path = "changeImage.do", method = RequestMethod.POST)
	public ModelAndView changeImage(int storyId, String newUrl, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:modifyStory.do");
		Story story = dao.findStoryById(storyId);
		User user = dao.findUserById(story.getUser().getId());
		User loggedInUser = (User) session.getAttribute("myUser");
		

		// Make sure the currently signed on user is the one changing the password
		// -- or is an admin
		if (user.getId() == loggedInUser.getId() || loggedInUser.getRole().equals("admin")) {
			Picture pic = new Picture();
			pic.setAlt("Story picture for: " + story.getName());
			pic.setUrl(newUrl);
			dao.addPicture(pic);
			story.getGenre().setPicture(pic);
			dao.updateStory(story);
			session.setAttribute("status", "Picture updated");
		} else {
			session.setAttribute("status", "You are not authorized to change this picture.");
		}
		mv.addObject("storyId", story.getId());

		return mv;
	}
}