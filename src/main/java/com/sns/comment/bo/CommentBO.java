package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.dao.CommentDAO;
import com.sns.comment.model.Comment;
import com.sns.comment.model.CommentView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	// 단방향 (one way)
	// timelineBO -> postBO	   -> userBO
	// 			  -> commentBO -> userBO
	
	@Autowired
	private UserBO userBO;

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
			
			// 댓글 쓴 사람
			User user = userBO.getUserById(comment.getUserId());
			commentView.setUser(user);
			
			commentViewList.add(commentView);
		}
		
		return commentViewList;
	}
}
