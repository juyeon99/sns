package com.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.post.dao.PostDAO;
import com.sns.post.model.Post;

@Service
public class PostBO {
	
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private PostDAO postDAO;
	
	public void addPost(int userId, String userLoginId, String content, MultipartFile file) {
		if(file != null) {
			String imagePath = fileManager.saveFile(userLoginId, file);
			postDAO.insertPost(userId, content, imagePath);
		}
	}

	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
}
