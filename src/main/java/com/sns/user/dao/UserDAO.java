package com.sns.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.user.model.User;

@Repository
public interface UserDAO {
	public List<User> selectUserList();
	
	public boolean existingID(String loginId);

	public void insertUser(
			@Param("loginId") String loginId, 
			@Param("password") String password, 
			@Param("name") String name, 
			@Param("email") String email,
			@Param("profileImagePath") String profileImagePath);
	
	public User selectUserByLoginId(
			@Param("loginId") String loginId, 
			@Param("password") String password);
	
	public User selectUserById(int id);
}
