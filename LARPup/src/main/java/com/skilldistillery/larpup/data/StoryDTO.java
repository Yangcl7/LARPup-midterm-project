package com.skilldistillery.larpup.data;

public class StoryDTO {
	private int storyId;
	private int userId;
	private String storyName;
	private String storyDescription;
	private String addressCity;
	private String addressState;
	private String addressZipcode;
	private String genreName;
	private String customGenreName;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStoryId() {
		return storyId;
	}
	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}
	public String getStoryName() {
		return storyName;
	}
	public void setStoryName(String storyName) {
		this.storyName = storyName;
	}
	public String getStoryDescription() {
		return storyDescription;
	}
	public void setStoryDescription(String storyDescription) {
		this.storyDescription = storyDescription;
	}
	public String getAddressCity() {
		return addressCity;
	}
	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}
	public String getAddressState() {
		return addressState;
	}
	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}
	public String getAddressZipcode() {
		return addressZipcode;
	}
	public void setAddressZipcode(String addressZipcode) {
		this.addressZipcode = addressZipcode;
	}
	public String getGenreName() {
		return genreName;
	}
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	
	public String getCustomGenreName() {
		return customGenreName;
	}
	public void setCustomGenreName(String customGenreName) {
		this.customGenreName = customGenreName;
	}
	@Override
	public String toString() {
		return "StoryDTO [storyId=" + storyId + ", storyName=" + storyName + ", storyDescription=" + storyDescription
				+ ", addressCity=" + addressCity + ", addressState=" + addressState + ", addressZipcode="
				+ addressZipcode + ", genreName=" + genreName + "]";
	}
	
	
}
