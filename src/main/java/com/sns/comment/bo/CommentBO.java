package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;

	public void saveComment(int userId, int postId, String content) {
		commentDAO.insertComment(userId, postId, content);
	}
	
	public List<Comment> getCommentListById(int postId){
		return commentDAO.selectCommentListById(postId);
	}
	
	public void deleteComment(int id) {
		commentDAO.deleteComment(id);
	}
	
}
