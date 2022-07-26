package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.model.User;

// view(jsp) -> Controller -> 			BO 			-> DAO -> Mapper.xml -> db
//			 				  TimelineBO -> PostBO
@Service
public class TimelineBO {
	
	// TimelineBO -> PostBO (자기 자신의 DAO는 부를 수 있지만, 남의 DAO는 부르면 안됨)
	// 상호참조 하면 안됨. (BO <-> BO	=> XXX)
	@Autowired
	private PostBO postBO;	
	
	@Autowired
	private UserBO userBO;	
	
	@Autowired
	private CommentBO commentBO;
	
	
	public List<CardView> generateCardList(){
		List<CardView> cardList = new ArrayList<>();
		List<Post> postList = postBO.getPostList();
		for (Post post : postList) {
			CardView card = new CardView();
			// 글 정보
			card.setPost(post);
			
			// 글 쓴 유저 정보
			User user = userBO.getUserById(post.getUserId());
			card.setUser(user);
			
			// 1:N 글:댓글	포스트의 댓글 정보
			int postId = post.getId();	// 글 번호
			
			// List<Comment> => List<CommentView>
//			List<Comment> commentList = commentBO.getCommentListByPostId(postId);
//			card.setCommentViewList(commentBO.getCommentListByPostId(postId));
			
			cardList.add(card);
		}
		
		return cardList;
	}
}
