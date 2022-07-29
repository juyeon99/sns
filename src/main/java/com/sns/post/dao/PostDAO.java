package com.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.model.Post;

@Repository
public interface PostDAO {
	
	public void insertPost(
			@Param("userId") int userId, 
			@Param("content") String content, 
			@Param("imagePath") String imagePath);

	public List<Post> selectPostList();

	public Post selectPostById(int postId);

	public void deletePostByPostIdAndUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);

	public List<Post> selectPostListByUserId(int userId);

	public int selectPostCount(int userId);
	
}
