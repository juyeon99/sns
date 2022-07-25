package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.Comment;
import com.sns.post.model.Post;

public class Card {
	private List<Comment> commentList;
	private Post post;
//	private String userLoginId;
	
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
//	public String getUserLoginId() {
//		return userLoginId;
//	}
//	public void setUserLoginId(String userLoginId) {
//		this.userLoginId = userLoginId;
//	}
}
