<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex justify-content-center">
	<div class="w-50 p-2">
		<div class="d-flex">
			<img src="${user.profileImagePath}" class="m-2 image-cropper"/>
			<div class="ml-5">
				<div class="d-flex align-items-center mt-3">
					<span style="font-size: 40px; font-weight: bold;" id="user-name">${user.name}</span>
					<button id="edit-btn" class="btn btn-secondary ml-5">Edit Profile</button>
				</div>
				<div class="d-flex align-items-center mt-4">
					<span style="font-size:20px;"><strong id="posts">${postCount}</strong> posts</span>
					<span class="ml-5" style="font-size:20px;"><strong id="followers">n</strong> followers</span>
					<span class="ml-5" style="font-size:20px;"><strong id="following">n</strong> following</span>
				</div>
				<div class="mt-4">
					<span id="introduce" style="font-size:20px;">${user.statusMessage}</span>
				</div>
			</div>
		</div>
		<hr>
		
		<div class="d-flex flex-wrap">
			<c:forEach var="post" items="${postList}">
				<div class="box d-flex align-items-center">
					<img src="${post.imagePath}" class="w-100 ">
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	$('#edit-btn').on('click',function(){
		
	});
});
</script>