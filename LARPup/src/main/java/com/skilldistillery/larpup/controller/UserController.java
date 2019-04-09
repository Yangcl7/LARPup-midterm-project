package com.skilldistillery.larpup.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.larpup.data.LarpUpDAO;
import com.skilldistillery.larpup.data.States;
import com.skilldistillery.larpup.data.UserDTO;
import com.skilldistillery.larpup.entities.Address;
import com.skilldistillery.larpup.entities.Picture;
import com.skilldistillery.larpup.entities.User;

@RestController
public class UserController {

	@Autowired
	private LarpUpDAO dao;

	@RequestMapping(path = { "displayUser.do" }, method = RequestMethod.GET)
	public ModelAndView userDisplay(Integer userId, HttpSession session) {
		ModelAndView mv = new ModelAndView("userPage");
		User user = dao.findUserById(userId);
		mv.addObject("ownerStoryList", dao.findStoriesOwnedByUserId(userId));
		mv.addObject("involvedStoryList", dao.findStoriesParticipatedInByUserId(userId));
		mv.addObject("roleList", dao.findEventUsersByUserId(userId));
		mv.addObject("user", user);

		User sessionUser = (User) session.getAttribute("myUser");
		if (sessionUser != null) {
			if (sessionUser.getRole().equals("admin")) {
				mv.setViewName("redirect:adminDisplay.do");
				mv.addObject("userId", user.getId());
			}
		}
		return mv;
	}

	@RequestMapping(path = { "adminDisplay.do" }, method = RequestMethod.GET)
	public ModelAndView adminDisplay(int userId, HttpSession session) {
		ModelAndView mv = new ModelAndView("userPage");
		User sessionUser = null;
		if (session != null) {
			sessionUser = (User) session.getAttribute("myUser");
		}
		mv.addObject("user", dao.findUserById(userId));

		// For the admin tab's 'User' list dropdowns
		if (sessionUser != null && sessionUser.getRole().equals("admin") && userId == sessionUser.getId()) {
			mv.addObject("allUsers", dao.findAllUsers());
			mv.addObject("filteredUsers", dao.findDeactivatedUsers());
		}
		
		mv.addObject("ownerStoryList", dao.findStoriesOwnedByUserId(userId));
		mv.addObject("involvedStoryList", dao.findStoriesParticipatedInByUserId(userId));
		mv.addObject("roleList", dao.findEventUsersByUserId(userId));
		
		return mv;
	}

	@RequestMapping(path = "updateUserForm.do", method = RequestMethod.GET)
	public ModelAndView updateUserForm(int userId, HttpSession session) {
		ModelAndView mv = new ModelAndView("userPage");
		UserDTO dto = new UserDTO();
		States states = new States();
		System.out.println(states);

		mv.addObject("user", dao.findUserById(userId));
		mv.addObject("userDTO", dto);
		mv.addObject("statesList", states);
		mv.addObject("action", "updateUser.do");
		return mv;
	}

	@RequestMapping(path = "updateUser.do", method = RequestMethod.POST)
	public ModelAndView updateUser(UserDTO userDTO, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:displayUser.do");
		User user = dao.findUserById(userDTO.getId());
		User loggedInUser = (User) session.getAttribute("myUser");

		// Make sure the currently signed on user is the one changing the password
		// -- or is an admin
		if (user.getId() == loggedInUser.getId() || loggedInUser.getRole().equals("admin")) {
			Address address = user.getAddress();
			address.setState(userDTO.getState());
			address.setCity(userDTO.getCity());
			address.setZipcode(userDTO.getZipcode());
			address.setStreet(userDTO.getStreet());
			dao.updateAddress(address);
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			user.setNickname(userDTO.getNickname());
			user.setEmail(userDTO.getEmail());
			user.setRole("user");
			user.setBirthDate(userDTO.getBirthDate());
			dao.updateUser(user);
			session.removeAttribute("status");
		} else {
			session.setAttribute("status", "You are not authorized to change this profile.");
			mv.addObject("userId", user.getId());
			mv.setViewName("redirect:updateUserForm.do");
			return mv;
		}

		mv.addObject("userId", user.getId());
		return mv;
	}

	@RequestMapping(path = { "createUserForm.do" }, method = RequestMethod.GET)
	public ModelAndView creatForm() {
		UserDTO dto = new UserDTO();
		States states = new States();

		ModelAndView mv = new ModelAndView("userForm");
		mv.addObject("inputDTO", dto);
		mv.addObject("statesList", states);
		mv.addObject("action", "createUser.do");
		return mv;
	}

	@RequestMapping(path = { "createUser.do" }, method = RequestMethod.POST)
	public ModelAndView addUser(UserDTO inputDTO) {
		ModelAndView mv = new ModelAndView();

		User tempUser = new User();
		tempUser.setFirstName(inputDTO.getFirstName());
		tempUser.setLastName(inputDTO.getLastName());
		tempUser.setNickname(inputDTO.getNickname());
		tempUser.setEmail(inputDTO.getEmail());
		tempUser.setPassword(inputDTO.getPassword());
		tempUser.setRole("user");
		tempUser.setBirthDate(inputDTO.getBirthDate());

		Address tempAddr = new Address();
		tempAddr.setStreet(inputDTO.getStreet());
		tempAddr.setCity(inputDTO.getCity());
		tempAddr.setState(inputDTO.getState());
		tempAddr.setZipcode(inputDTO.getZipcode());

		Picture pic = new Picture();
		pic.setUrl(getRandomProfilePic());
		pic.setAlt("random profile pic");
		dao.addPicture(pic);
		tempUser.setPicture(pic);

		tempUser.setAddress(dao.addAddress(tempAddr));
		mv.addObject("user", dao.addUser(tempUser));
		mv.setViewName("userPage");
		return mv;
	}

	@RequestMapping(path = { "deactivateUser.do" }, method = RequestMethod.GET)
	public ModelAndView deactivateUser(Integer userId, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:displayUser.do");
		User user = dao.findUserById(userId);
		User loggedInUser = (User) session.getAttribute("myUser");

		// Make sure an admin is signed on first
		if (user.getId() == loggedInUser.getId() || loggedInUser.getRole().equals("admin")) {
			user.setActive(false);
			dao.updateUser(user);
			mv.addObject("userId", user.getId());
		} else {
			mv.addObject("userId", user.getId());
		}

		return mv;
	}

	@RequestMapping(path = { "reactivateUser.do" }, method = RequestMethod.GET)
	public ModelAndView reactivateUser(int userId, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:displayUser.do");
		User user = dao.findUserById(userId);
		User loggedInUser = (User) session.getAttribute("myUser");

		// Make sure an admin is signed on first
		if (loggedInUser.getRole().equals("admin")) {
			user.setActive(true);
			dao.updateUser(user);
			mv.addObject("userId", user.getId());
		} else {
			mv.addObject("userId", user.getId());
		}
		return mv;
	}

	@RequestMapping(path = "changeUserImage.do", method = RequestMethod.POST)
	public ModelAndView changeImage(int userId, String newUrl, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:updateUserForm.do");
		User user = dao.findUserById(userId);
		User loggedInUser = (User) session.getAttribute("myUser");

		// Make sure the currently signed on user is the one changing the password
		// -- or is an admin
		if (user.getId() == loggedInUser.getId() || loggedInUser.getRole().equals("admin")) {
			Picture pic = new Picture();
			pic.setAlt(user.getNickname() + " profile picture");
			pic.setUrl(newUrl);
			dao.addPicture(pic);
			user.setPicture(pic);
			dao.updateUser(user);
			session.setAttribute("status", "Picture updated");
		} else {
			session.setAttribute("status", "You are not authorized to change this picture.");
		}
		mv.addObject("userId", user.getId());

		return mv;
	}

	@RequestMapping(path = "resetPassword.do", method = RequestMethod.POST)
	public ModelAndView resetPassword(int userId, String newPass, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:updateUserForm.do");
		User user = dao.findUserById(userId);
		User loggedInUser = (User) session.getAttribute("myUser");

		// Make sure the currently signed on user is the one changing the password
		// -- or is an admin
		if (user.getId() == loggedInUser.getId() || loggedInUser.getRole().equals("admin")) {
			user.setPassword(newPass);
			dao.updateUser(user);
			session.setAttribute("status", "Password changed successfully");
		} else {
			session.setAttribute("status", "You are not authorized to change this password.");
		}
		mv.addObject("userId", user.getId());

		return mv;
	}

	public String getRandomProfilePic() {
		List<String> list = new ArrayList<>();
		for(int i = 1; i < 27; i++) {
			
			list.add("resources/images/profilepics/pp" + i + ".png");
		}

		int index = (int) (Math.random() * list.size()) + 1;
		System.out.println(index);
		return list.get(index);
	}
}
