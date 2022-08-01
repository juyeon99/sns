<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex justify-content-center">
	<div class="w-25 m-5 p-2 bg-light">
		<div class="d-flex justify-content-center border-bottom pb-2">
			<div class="text-center col-6">
	    		<a href="/user/following_list_view?userId=${userId}&page=followers" class="d-block">${followers} Followers</a>
	    	</div>
    		<div class="text-center col-6">
    			<a href="/user/following_list_view?userId=${userId}&page=following" class="d-block">${following} Following</a>
    		</div>
		</div>
		
		<c:if test="${page eq 'followers'}">
		<c:forEach var="user" items="${followersList}">
			<div class="d-flex align-items-center">
				<div class="img-wrapper2 m-2">
					<a href="/user/profile_by_id_view?userId=${user.id}"><img src="${user.profileImagePath}"/></a>
				</div>
				<strong>${user.name}</strong>
			</div>
		</c:forEach>
		</c:if>
		
		<c:if test="${page eq 'following'}">
		<c:forEach var="user" items="${followingList}">
			<div class="d-flex align-items-center">
				<div class="img-wrapper2 m-2">
					<a href="/user/profile_by_id_view?userId=${user.id}"><img src="${user.profileImagePath}"/></a>
				</div>
				<strong>${user.name}</strong>
			</div>
		</c:forEach>
		</c:if>
	</div>
</div>