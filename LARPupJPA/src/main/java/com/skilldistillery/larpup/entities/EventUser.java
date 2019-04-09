package com.skilldistillery.larpup.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="event_user")
public class EventUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="event_user_info_id")
	private EventUserInfo eventUserInfo;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name="event_id")
	private Event event;
	
	@ManyToOne
	@JoinColumn(name="picture_id")
	private Picture picture;
	
	@Column(name="signup_date")
	@CreationTimestamp
	private Date signupDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EventUserInfo getEventUserInfo() {
		return eventUserInfo;
	}

	public void setEventUserInfo(EventUserInfo eventUserInfo) {
		this.eventUserInfo = eventUserInfo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public Date getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(Date signupDate) {
		this.signupDate = signupDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event == null) ? 0 : event.hashCode());
		result = prime * result + ((eventUserInfo == null) ? 0 : eventUserInfo.hashCode());
		result = prime * result + id;
		result = prime * result + ((picture == null) ? 0 : picture.hashCode());
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
		EventUser other = (EventUser) obj;
		if (event == null) {
			if (other.event != null)
				return false;
		} else if (!event.equals(other.event))
			return false;
		if (eventUserInfo == null) {
			if (other.eventUserInfo != null)
				return false;
		} else if (!eventUserInfo.equals(other.eventUserInfo))
			return false;
		if (id != other.id)
			return false;
		if (picture == null) {
			if (other.picture != null)
				return false;
		} else if (!picture.equals(other.picture))
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
		return "EventUser [id=" + id + ", eventUserInfo=" + eventUserInfo + ", user=" + user + ", event=" + event
				+ ", picture=" + picture + "]";
	}
}
