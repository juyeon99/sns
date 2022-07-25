package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.post.bo.PostBO;
import com.sns.post.model.Post;
import com.sns.timeline.model.Card;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	public List<Card> generateCardList(){
		List<Card> cardList = new ArrayList<>();
		List<Post> postList = postBO.getPostList();
		for (int i = 0; i < postList.size(); i++) {
			Card card = new Card();
			card.setPost(postList.get(i));
			int postId = postList.get(i).getId();
			card.setCommentList(commentBO.getCommentListById(postId));
			cardList.add(card);
		}
		
		return cardList;
	}
}
