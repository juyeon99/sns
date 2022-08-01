package com.sns.follow.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sns.follow.model.Follow;

@Repository
public interface FollowDAO {

	public void insertFollow(
			@Param("requestedUserId") int requestedUserId, 
			@Param("acceptedUserId") int acceptedUserId);

	public void deleteFollow(
			@Param("requestedUserId") int requestedUserId, 
			@Param("acceptedUserId") int acceptedUserId);

	public boolean selectFollow(
			@Param("requestedUserId") int requestedUserId, 
			@Param("acceptedUserId") int acceptedUserId);

	public int countFollowers(int userId);

	public int countFollowings(int userId);

	public List<Follow> selectFollowersList(int userId);
	
	public List<Follow> selectFollowingList(int userId);
}
