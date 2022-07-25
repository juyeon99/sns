package com.sns.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;

	public void saveComment(int userId, int postId, String content) {
		commentDAO.insertComment(userId, postId, content);
	}
	
}
