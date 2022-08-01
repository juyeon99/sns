package com.sns.follow.model;

import java.util.Date;

public class Follow {
	private int id;
	private int followRequestedUserId;
	private int followAcceptedUserId;
	private Date createdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFollowRequestedUserId() {
		return followRequestedUserId;
	}
	public void setFollowRequestedUserId(int followRequestedUserId) {
		this.followRequestedUserId = followRequestedUserId;
	}
	public int getFollowAcceptedUserId() {
		return followAcceptedUserId;
	}
	public void setFollowAcceptedUserId(int followAcceptedUserId) {
		this.followAcceptedUserId = followAcceptedUserId;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
