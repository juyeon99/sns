package com.sns.timeline.model;

import java.util.List;

import com.sns.comment.model.CommentView;
import com.sns.like.model.Like;
import com.sns.post.model.Post;
import com.sns.user.model.User;

public class CardView {		// Entity가 아니라 DTO (View용 object)
	
	private User user;							// 카드의 주인, 글 쓴 사람	${card.user.name}
	private List<CommentView> commentViewList;	// 글에 대한 댓글들	${card.commentList.comment.content}
	private Post post;							// 글 내용	${card.post.content}
	private boolean filledLike;					// 내가 좋아요 눌렀는지 여부
	private int likeCount;						// 좋아요 개수
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<CommentView> getCommentViewList() {
		return commentViewList;
	}
	public void setCommentViewList(List<CommentView> commentViewList) {
		this.commentViewList = commentViewList;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public boolean isFilledLike() {
		return filledLike;
	}
	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
}
