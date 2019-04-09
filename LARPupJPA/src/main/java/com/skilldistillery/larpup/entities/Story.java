package com.skilldistillery.larpup.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="genre_id")
	private Genre genre;
	
	@OneToMany(mappedBy="story")
	private List<Event> events;
	
	@OneToMany(mappedBy="story")
	private List<EventUserInfo> eventRoles;
	
	@Column(name="create_date")
	@CreationTimestamp
	private Date createDate;
	
	public void addEventUserInfo(EventUserInfo evtUsrNfo) {
		if(eventRoles == null)
			eventRoles = new ArrayList<>();
		if(!eventRoles.contains(evtUsrNfo)) {
			eventRoles.add(evtUsrNfo);
			evtUsrNfo.setStory(this);
		}
	}
	
	public void removeEventUserInfo(EventUserInfo evtUsrNfo) {
		evtUsrNfo.setStory(null);
		if (eventRoles != null) {
			eventRoles.remove(evtUsrNfo);
		}
	}
	
	public void addEvent(Event event) {
		if(events == null)
			events = new ArrayList<>();
		if(!events.contains(event)) {
			events.add(event);
			event.setStory(this);
		}
	}
	
	public void removeEvent(Event event) {
		event.setStory(null);
		if (events != null) {
			events.remove(event);
		}
	}
	
	public List<EventUserInfo> getEventRoles() {
		return eventRoles;
	}

	public void setEventRoles(List<EventUserInfo> eventUserInfo) {
		this.eventRoles = eventUserInfo;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Story other = (Story) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("ID: %d%s", id, "<br>"));
		sb.append(String.format("Name: %s%s", name, "<br>"));
		sb.append(String.format("Description: %s%s", description, "<br>"));
		sb.append(String.format("Genre: %s%s", genre.getName(), "<br>"));
		sb.append(String.format("Created by: %s%s", user.getNickname(), "<br>"));
		sb.append(String.format("Created on: %s%s", createDate, "<br>"));
		return sb.toString();
	}
}
