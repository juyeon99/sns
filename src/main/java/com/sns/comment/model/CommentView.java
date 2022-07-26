package com.sns.comment.model;

import com.sns.user.model.User;

public class CommentView {	// Entity가 아니라 DTO (View용 object)
	private User user;		// 댓글 쓴 사람
	private Comment comment;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Comment getComment() {
		return comment;
	}
	public void setComment(Comment comment) {
		this.comment = comment;
	}
}
