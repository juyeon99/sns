package com.sns.follow.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.dao.FollowDAO;
import com.sns.follow.model.Follow;

@Service
public class FollowBO {

	@Autowired
	private FollowDAO followDAO;
	
	public void addFollow(int requestedUserId, int acceptedUserId) {
		followDAO.insertFollow(requestedUserId, acceptedUserId);
	}

	public void deleteFollow(int requestedUserId, int acceptedUserId) {
		followDAO.deleteFollow(requestedUserId, acceptedUserId);
	}

	public boolean existFollow(int requestedUserId, int acceptedUserId) {
		return followDAO.selectFollow(requestedUserId, acceptedUserId);
	}
	
	public int countFollowers(int userId) {
		return followDAO.countFollowers(userId);
	}
	
	public int countFollowings(int userId) {
		return followDAO.countFollowings(userId);
	}
	
	public List<Follow> getFollowersList(int userId) {
		return followDAO.selectFollowersList(userId);
	}
	
	public List<Follow> getFollowingList(int userId) {
		return followDAO.selectFollowingList(userId);
	}

}
