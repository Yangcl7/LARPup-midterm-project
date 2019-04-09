package com.skilldistillery.larpup.data;

import java.text.ParseException;
import java.time.LocalDateTime;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class EventDTO {
	private int id;
	private int storyId;
	private String name;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime date;
	private String street;
	private String city;
	private String state;
	private String zipcode;
	
	public int getStoryId() {
		return storyId;
	}
	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	public void setDate(String date) throws ParseException {
		this.date = LocalDateTime.parse(date);
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
