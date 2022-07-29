package com.sns.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.dao.CommentDAO;
import com.sns.common.FileManagerService;
import com.sns.like.dao.LikeDAO;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	
//	private Logger logger = LoggerFactory.getLogger(PostBO.class);		// slf4j.Logger
	private Logger logger = LoggerFactory.getLogger(this.getClass());	// sysout 대신에 로그 찍어서 확인
	
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private LikeDAO likeDAO;
	
	public void addPost(int userId, String userLoginId, String content, MultipartFile file) {
		if(file != null) {
			String imagePath = fileManager.saveFile(userLoginId, file);
			postDAO.insertPost(userId, content, imagePath);
		}
	}

	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	public Post getPostById(int postId) {
		return postDAO.selectPostById(postId);
	}

	public void deletePostByPostIdAndUserId(int postId, int userId) {
		// 삭제할 글을 select
		Post post = getPostById(postId);
		if(post == null) {	// 삭제할 post가 없으면 error
			logger.error("[delete post] 삭제할 게시물이 없습니다. postId:{}",postId);
			return;			// 메소드 종료
		}
		
		// image가 있으면 image 삭제
		if(post.getImagePath() != null) {
			try {
				fileManager.deleteFile(post.getImagePath());
			} catch (IOException e) {
				logger.error("이미지 삭제 실패. postId:{}",postId);
			}
		}
		
		// 글 삭제 by postId
		postDAO.deletePostByPostIdAndUserId(postId,userId);
		
		// 댓글들 삭제 by postId
		commentDAO.deleteCommentByPostId(postId);
		
		// 좋아요 삭제 by postId
		likeDAO.deleteLikeByPostId(postId);
	}

	public List<Post> getPostListByUserId(int userId) {
		return postDAO.selectPostListByUserId(userId);
	}
	
	public int getPostCount(int userId) {
		return postDAO.selectPostCount(userId);
	}
	
}
