package com.sns.user.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.dao.UserDAO;
import com.sns.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;

	public List<User> getUserList() {
		return userDAO.selectUserList();
	}

	public boolean existingID(String loginId) {
		return userDAO.existingID(loginId);
	}

	public void addUser(String loginId, String password, String name, String email) {
		userDAO.insertUser(loginId, password, name, email);
	}
	
	public User getUserById(String loginId, String password) {
		return userDAO.selectUserById(loginId, password);
	}

}
