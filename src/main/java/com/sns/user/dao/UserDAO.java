package com.sns.user.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sns.user.model.User;

@Repository
public interface UserDAO {
	public List<User> selectUserList();
	
	public boolean existingID(String loginId);
}
