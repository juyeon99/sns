package com.sns.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.like.model.Like;

@Repository
public interface LikeDAO {

	public Like selectLike(
			@Param("userId") int userId, 
			@Param("postId") int postId);

	public void deleteLike(
			@Param("userId") int userId, 
			@Param("postId") int postId);

	public void insertLike(
			@Param("userId") int userId, 
			@Param("postId") int postId);

	public int selectLikeCount(int postId);

	public void deleteLikeByPostId(int postId);

}
