package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;

	public void saveComment(int userId, int postId, String content) {
		commentDAO.insertComment(userId, postId, content);
	}
	
	public List<Comment> getCommentListByPostId(int postId){
		return commentDAO.selectCommentListByPostId(postId);
	}
	
	public void deleteComment(int id) {
		commentDAO.deleteComment(id);
	}
	
	public List<CommentView> generateCommentViewListByPostId(int postId){
		List<CommentView> commentViewList = new ArrayList<>();
		List<Comment> commentList = getCommentListByPostId(postId);
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			commentView.setComment(comment);
			// set comment user
			
		}
		
		
		return ;
	}
}
