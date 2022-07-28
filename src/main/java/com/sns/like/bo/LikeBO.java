package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public void like(int userId, int postId) {
		if(likeDAO.selectLike(userId, postId) != null) {		// like를 한적 있음 => 좋아요 해제
			likeDAO.deleteLike(userId, postId);
		} else {	// like를 한적 없음 => 좋아요 insert
			likeDAO.insertLike(userId, postId);
		}
	}

	public boolean existLike(Integer userId, int postId) {
		if(likeDAO.selectLike(userId, postId) !=  null) {
			return true;
		}
		return false;
	}

	public int getLikeCount(int postId) {
		return likeDAO.selectLikeCount(postId);
	}
	
}
