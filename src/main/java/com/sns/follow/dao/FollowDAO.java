package com.sns.follow.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

}
