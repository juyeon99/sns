package com.sns.user.bo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.common.FileManagerService;
import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private UserDAO userDAO;

	public List<User> getUserList() {
		return userDAO.selectUserList();
	}

	public boolean existingID(String loginId) {
		return userDAO.existingID(loginId);
	}

	public void addUser(String loginId, String password, String name, String email, String profileImagePath) {
		userDAO.insertUser(loginId, password, name, email, profileImagePath);
	}
	
	public User getUserByLoginId(String loginId, String password) {
		return userDAO.selectUserByLoginId(loginId, password);
	}
	
	public User getUserById(int id) {
		return userDAO.selectUserById(id);
	}

	public void updateUser(int id, String name, String statusMessage, MultipartFile file) {
		User user = getUserById(id);
		
		String profileImagePath = null;
		// upload할 file이 있는지 본 후 서버에 upload하고 imagePath를 받아온다.
		if(file != null) {
			profileImagePath = fileManager.saveFile(name + "_pfp", file);
			
			// 새로운 이미지가 존재하면 기존 이미지 삭제 (기존 이미지가 있을 때에만)
			if(profileImagePath != null && user.getProfileImagePath() != null) {
				// default pfp가 아니면 기존 이미지 삭제
				if(!user.getProfileImagePath().equals("/images/default_pfp/pfp.png")) {
					try {
						fileManager.deleteFile(user.getProfileImagePath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		} else {
			profileImagePath = user.getProfileImagePath();
		}
		
		
		//String profileImagePath = fileManager.saveFile(name + "_pfp", file);
		userDAO.updateUser(id, name, statusMessage, profileImagePath);
	}

}
