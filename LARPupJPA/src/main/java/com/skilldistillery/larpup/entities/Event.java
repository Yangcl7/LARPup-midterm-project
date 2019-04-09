package com.skilldistillery.larpup.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	private String description;
	private LocalDateTime date;
	
	@Column(name="create_date")
	@CreationTimestamp
	private Date createDate;

	@ManyToOne
	@JoinColumn(name = "story_id")
	private Story story;
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy="event")
	private List<EventUser> eventUsers;
	
	public String getFormattedDate() {
		String monthValue = "" + date.getMonthValue();
		String dayValue = "" + date.getDayOfMonth();
		if(monthValue.length() == 1) {
			monthValue = "0" + monthValue;
		}
		if(dayValue.length() == 1) {
			dayValue = "0" + dayValue;
		}
		String d = date.getYear() + "-" + monthValue + "-" + dayValue;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
		LocalDate ld = LocalDate.parse(d, dtf);
		String formattedDate = dtf2.format(ld);
		
		String time, hour, minute;
		hour = "" + date.getHour();
		minute = "" + date.getMinute();
		if(hour.length() == 1) {
			hour = "0" + hour;
		}
		if(minute.length() == 1) {
			minute = "0" + minute;
		}
		time = hour + ":" + minute;
		if(Integer.parseInt(hour) > 0 && Integer.parseInt(hour) < 12) {
			time += " AM";
		}else {
			time += " PM";
		}
		
		return (formattedDate + " / " + time);
	}
	
	public void addEventUser(EventUser usr) {
		if(eventUsers == null)
			eventUsers = new ArrayList<>();
		if(!eventUsers.contains(usr)) {
			eventUsers.add(usr);
			usr.setEvent(this);
		}
	}
	
	public void removeEventUser(EventUser usr) {
		usr.setEvent(null);
		if (eventUsers != null) {
			eventUsers.remove(usr);
		}
	}
	
	public List<EventUser> getEventUsers() {
		return eventUsers;
	}

	public void setEventUsers(List<EventUser> eventUsers) {
		this.eventUsers = eventUsers;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime localDateTime) {
		this.date = localDateTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Story getStory() {
		return story;
	}

	public void setStory(Story story) {
		this.story = story;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((eventUsers == null) ? 0 : eventUsers.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((story == null) ? 0 : story.hashCode());
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
		Event other = (Event) obj;
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
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eventUsers == null) {
			if (other.eventUsers != null)
				return false;
		} else if (!eventUsers.equals(other.eventUsers))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (story == null) {
			if (other.story != null)
				return false;
		} else if (!story.equals(other.story))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("ID: %d%s", id, "<br>"));
		sb.append(String.format("Name: %s%s", name, "<br>"));
		sb.append(String.format("Description: %s%s", description, "<br>"));
		sb.append(String.format("Event Date/Time: %s%s", date, "<br>"));
		sb.append(String.format("Created on: %s%s", createDate, "<br>"));
		return sb.toString();
	}
}
