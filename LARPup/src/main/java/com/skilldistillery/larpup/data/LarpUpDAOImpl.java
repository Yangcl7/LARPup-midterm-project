package com.skilldistillery.larpup.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.skilldistillery.larpup.entities.Address;
import com.skilldistillery.larpup.entities.Event;
import com.skilldistillery.larpup.entities.EventComment;
import com.skilldistillery.larpup.entities.EventUser;
import com.skilldistillery.larpup.entities.EventUserInfo;
import com.skilldistillery.larpup.entities.Genre;
import com.skilldistillery.larpup.entities.Picture;
import com.skilldistillery.larpup.entities.Story;
import com.skilldistillery.larpup.entities.StoryComment;
import com.skilldistillery.larpup.entities.User;

@Transactional
@Service
public class LarpUpDAOImpl implements LarpUpDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	public User findUserById (int id) {
		return em.find(User.class, id);
	}

	@Override
	public User findUserByEmail(String email) {
		String query = "SELECT u FROM User u WHERE u.email = :email";
		User user = null;
		try {
			user = em.createQuery(query, User.class).setParameter("email", email).getSingleResult();
		}catch(Exception e) {
		}
		return user;
	}
	
	@Override
	public List<User> findUsersByString(String str){
		String query = "SELECT u FROM User u WHERE u.firstName LIKE :str OR u.lastName LIKE :str OR u.nickname LIKE :str OR u.email LIKE :str";
		return em.createQuery(query, User.class).setParameter("str", "%" + str + "%").getResultList();
	}
	
	@Override
	public List<User> findAllUsers() {
		return em.createQuery("SELECT u FROM User u", User.class).getResultList();
	}
	
	@Override
	public List<User> findDeactivatedUsers(){
		String query = "SELECT u FROM User u WHERE active = 'false'";
		return em.createQuery(query, User.class).getResultList();
	}
	
	@Override
	public User addUser(User user) {
		em.persist(user);
		em.flush();
		return user;
	}
	
	@Override
	public boolean updateUser(User user) {
		User managed = em.find(User.class, user.getId());

		if (managed == null) {
			return false;
		}

		try {
			managed.setBirthDate(user.getBirthDate());
			managed.setPicture(user.getPicture());
			managed.setAddress(user.getAddress());
			managed.setCreateDate(user.getCreateDate());
			managed.setEmail(user.getEmail());
			managed.setEventRoles(user.getEventRoles());
			managed.setFirstName(user.getFirstName());
			managed.setLastName(user.getLastName());
			managed.setNickname(user.getNickname());
			managed.setPassword(user.getPassword());
			managed.setActive(user.isActive());

			em.persist(managed);
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public Story findStoryById(int id) {
		return em.find(Story.class, id);
	}
	
	@Override
	public List<Story> findStoriesOwnedByUserId(int id) {
		String query = "SELECT story FROM Story story WHERE story.user.id = :id";		
		return em.createQuery(query, Story.class).setParameter("id", id).getResultList();
	}

	@Override
	public List<Story> findStoriesParticipatedInByUserId(int id) {
		String query = "SELECT DISTINCT story FROM Story story "
					 + "JOIN story.events event "
					 + "JOIN event.eventUsers evtUsr "
					 + "WHERE evtUsr.user.id = :id";		
		return em.createQuery(query, Story.class).setParameter("id", id).getResultList();
	}
	
	@Override
	public List<Story> findStoriesBySearchString(String str){
		String query = "SELECT s FROM Story s WHERE s.name LIKE :str OR s.description LIKE :str";
		return em.createQuery(query, Story.class).setParameter("str", "%" + str + "%").getResultList();
	}
	
	
	@Override
	public List<Story> getRecentStories(int count) {
		List<Story> list = null;
		String query = "SELECT story FROM Story story ORDER BY story.createDate DESC";
		list = em.createQuery(query, Story.class).getResultList();
		
		return list.size() <= count ? list : list.subList(0, count);
	}
	
	@Override
	public Story addStory(Story story) {
		em.persist(story);
		em.flush();
		return story;
	}
	
	@Override
	public boolean updateStory(Story story) {
		Story managed = em.find(Story.class, story.getId());
		
		if (managed == null)
			return false;
		
		try {
			managed.setAddress(story.getAddress());
			managed.setCreateDate(story.getCreateDate());
			managed.setDescription(story.getDescription());
			managed.setEventRoles(story.getEventRoles());
			managed.setEvents(story.getEvents());
			managed.setGenre(story.getGenre());
			managed.setName(story.getName());
			managed.setUser(story.getUser());
			
			em.persist(managed);
			em.flush();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	public Event findEventById(int id) {
		return em.find(Event.class, id);
	}
	
	@Override
	public List<Event> findEventsBySearchString(String str){
		String query = "SELECT e FROM Event e WHERE e.name LIKE :str OR e.description LIKE :str";
		return em.createQuery(query, Event.class).setParameter("str", "%" + str + "%").getResultList();
	}
	
	@Override
	public Event addEvent(Event event) {
		em.persist(event);
		em.flush();
		return event;
	}
	
	@Override
	public boolean updateEvent(Event event) {
		Event managed = em.find(Event.class, event.getId());
		
		if (managed == null)
			return false;
		
		try {
			managed.setAddress(event.getAddress());
			managed.setCreateDate(event.getCreateDate());
			managed.setDate(event.getDate());
			managed.setDescription(event.getDescription());
			managed.setEventUsers(event.getEventUsers());
			managed.setName(event.getName());
			managed.setStory(event.getStory());
			
			em.persist(managed);
			em.flush();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public StoryComment findStoryCommentById(int id) {
		return em.find(StoryComment.class, id);
	}
	
	@Override
	public EventComment findEventCommentById(int id) {
		return em.find(EventComment.class, id);
	}
	
	@Override
	public StoryComment addStoryComment(StoryComment storyComment) {
		em.persist(storyComment);
		em.flush();
		return storyComment;
	}
	
	@Override
	public EventComment addEventComment(EventComment eventComment) {
		em.persist(eventComment);
		em.flush();
		return eventComment;
	}
	
	@Override
	public Picture findPictureById(int id) {
		return em.find(Picture.class, id);
	}
	
	@Override
	public Picture findPictureByUrl(String url) {
		List<Picture> pic = null;
		String query = "SELECT pic FROM Picture pic WHERE pic.url = :url";
		pic = em.createQuery(query, Picture.class).setParameter("url", url).getResultList();
		
		return pic.size() > 0 ? pic.get(0) : null;
	}
	
	@Override
	public Picture addPicture(Picture picture) {
		em.persist(picture);
		em.flush();
		return picture;
	}
	
	@Override
	public boolean updatePicture(Picture picture) {
		Picture managed = em.find(Picture.class, picture.getId());
		
		if (managed == null)
			return false;
		
		try {
			managed.setAlt(picture.getAlt());
			managed.setUrl(picture.getUrl());
			
			em.persist(managed);
			em.flush();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	@Override
	public EventUser findEventUserById(int id) {
		return em.find(EventUser.class, id);
	}
	
	@Override
	public List<EventUser> findEventUsersByUserId(int id){
		return em.createQuery("SELECT role FROM EventUser role WHERE role.user.id = :id", EventUser.class).setParameter("id", id).getResultList();
	}
	
	@Override
	public EventUser addEventUser(EventUser eventUser) {
		em.persist(eventUser);
		em.flush();
		return eventUser;
	}
	
	@Override
	public void removeEventUser(EventUser eventUser) {
		em.remove(eventUser);
	}
	
	@Override
	public boolean updateEventUser(EventUser eventUser) {
		EventUser managed = em.find(EventUser.class, eventUser.getId());
		
		if (managed == null)
			return false;
		
		try {
			managed.setEvent(eventUser.getEvent());
			managed.setEventUserInfo(eventUser.getEventUserInfo());
			managed.setPicture(eventUser.getPicture());
			managed.setSignupDate(eventUser.getSignupDate());
			managed.setUser(eventUser.getUser());
			
			em.persist(managed);
			em.flush();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	public EventUserInfo findEventUserInfoById(int id) {
		return em.find(EventUserInfo.class, id);
	}
	
	@Override
	public EventUserInfo addEventUserInfo(EventUserInfo eventUserInfo) {
		em.persist(eventUserInfo);
		em.flush();
		return eventUserInfo;
	}
	
	@Override
	public boolean updateEventUserInfo(EventUserInfo eventUserInfo) {
		EventUserInfo managed = em.find(EventUserInfo.class, eventUserInfo.getId());
		
		if (managed == null)
			return false;
		
		try {
			managed.setDescription(eventUserInfo.getDescription());
			managed.setName(eventUserInfo.getName());
			managed.setStory(eventUserInfo.getStory());
			
			em.persist(managed);
			em.flush();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	public Address findAddressById(int id) {
		return em.find(Address.class, id);
	}
	
	@Override
	public Address addAddress(Address address) {
		em.persist(address);
		em.flush();
		return address;
	}
	
	@Override
	public boolean updateAddress(Address address) {
		Address managed = em.find(Address.class, address.getId());
		
		if (managed == null)
			return false;
		
		try {
			managed.setCity(address.getCity());
			managed.setPhone(address.getPhone());
			managed.setState(address.getState());
			managed.setStreet(address.getStreet());
			managed.setZipcode(address.getZipcode());
			
			em.persist(managed);
			em.flush();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	public Genre findGenreById(int id) {
		return em.find(Genre.class, id);
	}
	
	@Override
	public List<Genre> findGenresByName(String name) {
		String query = "SELECT g FROM Genre g WHERE g.name LIKE :name";
		return em.createQuery(query, Genre.class).setParameter("name", "%" + name + "%").getResultList();
	}
	
	@Override
	public Genre addGenre(Genre genre) {
		em.persist(genre);
		em.flush();
		return genre;
	}
	
	@Override
	public boolean updateGenre(Genre genre) {
		Genre managed = em.find(Genre.class, genre.getId());
		
		if (managed == null)
			return false;
		
		try {
			managed.setName(genre.getName());
			managed.setPicture(genre.getPicture());
			
			em.persist(managed);
			em.flush();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}





