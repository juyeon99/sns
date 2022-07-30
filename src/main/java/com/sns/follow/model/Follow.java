package com.sns.follow.model;

import java.util.Date;

public class Follow {
	private int id;
	private String followRequestedUserId;
	private String followAcceptedUserId;
	private Date createdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFollowRequestedUserId() {
		return followRequestedUserId;
	}
	public void setFollowRequestedUserId(String followRequestedUserId) {
		this.followRequestedUserId = followRequestedUserId;
	}
	public String getFollowAcceptedUserId() {
		return followAcceptedUserId;
	}
	public void setFollowAcceptedUserId(String followAcceptedUserId) {
		this.followAcceptedUserId = followAcceptedUserId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
