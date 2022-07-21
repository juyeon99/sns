package com.sns.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.dao.PostDAO;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public void addPost(int userId, String userLoginId, String content, MultipartFile file) {
		String imagePath = "";
		
		postDAO.insertPost(userId, content, imagePath);
	}
	
}
