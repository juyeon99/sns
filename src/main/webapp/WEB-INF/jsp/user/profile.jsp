<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex justify-content-center">
	<div class="w-50 p-2">
		<div class="d-flex">
			<div id="img-wrapper3">
				<img src="${user.profileImagePath}" class="m-2"/>
			</div>
			<div class="ml-5">
				<div class="d-flex align-items-center mt-3">
					<span style="font-size: 40px; font-weight: bold;" id="user-name">${user.name}</span>
					
					<!-- 로그인이 되어있는 상태일 때 -->
					<c:if test="${not empty userId}">
						<!-- 내 프로필일 때 -->
						<c:if test="${user.id eq userId}">
							<button class="btn btn-secondary ml-5" data-toggle="modal" data-target="#editProfileModal">Edit Profile</button>
						</c:if>
						
						<!-- 다른 사람 프로필일 때 -->
						<c:if test="${user.id ne userId}">
							<c:if test="${!followed}">
								<button id="follow-btn" class="btn btn-secondary ml-5" data-follow-id="${user.id}">Follow</button>
							</c:if>
							<c:if test="${followed}">
								<button id="unfollow-btn" class="btn btn-secondary ml-5" data-unfollow-id="${user.id}">Unfollow</button>
							</c:if>
						</c:if>
					</c:if>
				</div>
				<div class="d-flex align-items-center mt-4">
					<span style="font-size:20px;"><strong id="posts">${postCount}</strong> posts</span>
					<a href="/user/following_list_view?userId=${user.id}&page=followers">
						<span class="ml-5" style="font-size:20px; color:black;"><strong id="followers">${followers}</strong> followers</span>
					</a>
					<a href="/user/following_list_view?userId=${user.id}&page=following">
						<span class="ml-5" style="font-size:20px; color:black;"><strong id="following">${following}</strong> following</span>
					</a>
				</div>
				<div class="mt-4">
					<span id="introduce" style="font-size:20px;">${user.statusMessage}</span>
				</div>
			</div>
		</div>
		<hr>
		
		<c:if test="${empty postList}">
			<h1 class="text-center m-5">No posts</h1>
		</c:if>
		
		<div class="d-flex flex-wrap">
			<c:forEach var="post" items="${postList}">
				<div class="box d-flex align-items-center">
					<img src="${post.imagePath}" class="w-100 ">
				</div>
			</c:forEach>
		</div>
	</div>
</div>

<div class="modal fade" id="editProfileModal">
	<div class="modal-dialog modal-sm modal-dialog-centered">
    	<div class="modal-content">
    		<div class="p-2 d-flex align-items-center flex-column">
    			<!-- 프로필 이미지 변경 -->
    			<div id="img-wrapper">
    				<img id="profile-img" src="${user.profileImagePath}" class="image-cropper2"/>
    			</div>
    			
    			<input type="file" id="file" class="d-none" accept=".jpg,.jpeg,.gif,.png">
    			<a href="#" class="change-pfp">Change profile photo</a>
    		</div>
    		
    		<div class="p-2 border-top d-flex align-items-center">
    			<span class="col-3">Name</span>
    			<input type="text" id="name" class="form-control" placeholder="이름" value="${user.name}">
    		</div>
    		
    		<div class="p-2 border-top d-flex align-items-center">
    			<span class="col-3">Bio</span>
    			<input type="text" id="bio" class="form-control" placeholder="소개" value="${user.statusMessage}">
    		</div>
    		
    		<div class="text-center py-2 border-top">
    			<a href="#" id="edit-btn" class="d-block">Done</a>
    		</div>
    		<div class="text-center py-2 border-top">
    			<a href="#" class="d-block text-danger" data-dismiss="modal">취소</a>
    		</div>
    	</div>
  	</div>
</div>

<script>
$(document).ready(function(){
	$('#follow-btn').on('click',function(){
		let acceptedUserId = $(this).data('follow-id');		// 팔로우 당하는 사람
		
		$.ajax({
			// request
			type: "POST"
			,url: "/follow/create"
			,data: {"acceptedUserId":acceptedUserId}
			
			// response
			,success: function(data){
				if(data.result == "success"){
					location.reload(true);
				} else{
					alert(data.errorMesssage);
				}
			}
			,error: function(e){
				alert("팔로우 실패");
			}
		});
	});

	$('#unfollow-btn').on('click',function(){
		let acceptedUserId = $(this).data('unfollow-id');
		
		$.ajax({
			// request
			type: "DELETE"
			,url: "/follow/delete"
			,data: {"acceptedUserId":acceptedUserId}
			
			// response
			,success: function(data){
				if(data.result == "success"){
					location.reload(true);
				} else{
					alert(data.errorMesssage);
				}
			}
			,error: function(e){
				alert("언팔로우 실패");
			}
		});
	});
	
	$('.change-pfp').on('click',function(e){
		e.preventDefault();		// a 태그를 클릭했을 때 창이 위로 올라가는 것을 방지
		$('#file').click();		// input file을 클릭한 것과 같은 효과
	});
	
	// 파일 업로드를 했을 때, 파일 이름 노출, 파일 확장자 검증
	$('#file').on('change',function(e){		// 태그의 변화 감지 (이미지가 선택됐을 때)
		let fileName = e.target.files[0].name;	// ex) image.jpg
		let arr = fileName.split(".");
		
		// 확장자 검증
		if(arr.length < 2 || 
				(arr[arr.length-1].toLowerCase() != 'gif'
						&& arr[arr.length-1].toLowerCase() != 'jpg'
						&& arr[arr.length-1].toLowerCase() != 'jpeg'
						&& arr[arr.length-1].toLowerCase() != 'png')){
			alert("이미지 파일만 업로드 가능합니다.");
			$(this).val("");			// 서버에 잘못된 파일이 올라가기 전에 파일을 비움
			return;
		}
		
		setImageFromFile(this, '#profile-img');
	});
	
	// 선택한 파일 미리보기
	// reference: https://chlee21.tistory.com/193
	function setImageFromFile(input, expression) {
	    if (input.files && input.files[0]) {
	    	var reader = new FileReader();
	    	reader.onload = function (e) {
	    		// $("#profile-img").attr("src",file_path);
	    		$(expression).attr('src', e.target.result);
	    	}
	  		reader.readAsDataURL(input.files[0]);
	    }
	}
	
	// modal창 안에 있는 수정완료 버튼 클릭
	$('#editProfileModal #edit-btn').on('click',function(e){
		e.preventDefault();
		
		let name = $('#name').val().trim();
		if (name == "") {
			alert("이름을 입력하세요");
			return false;
		}
		let bio = $('#bio').val();
		
		// image file을 보내기 위해 form 태그를 자바스크립트에서 만든다.
		let formData = new FormData();
		formData.append("name", name);
		formData.append("bio", bio);
		formData.append("file", $('#file')[0].files[0]);
		
		// 유저 정보 업데이트 요청
		$.ajax({
			type:"POST",
			url: "/user/edit_profile",
			data: formData,
			encType: "multipart/form-data",	// file 업로드 필수 설정
			processData: false,				// file 업로드 필수 설정 (이미지 파일이므로 data를 string으로 변환하지 않하게 함)
			contentType: false,				// file 업로드 필수 설정
			
			success: function(data){
				if(data.result == "success"){
					alert("프로필을 변경하였습니다.");
					location.reload(true);
				}else{
					alert(data.errorMessage);
				}
			},
			error: function(e){
				alert("프로필을 편집하는데 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
	
	/* $('#showFollowersList').on('click',function(e){
		let userId = $(this).data('followers-id');
		
		$.ajax({
			type:"GET",
			url: "/user/following_list_view",
			data: {"userId":userId},
			success: function(data){
				if(data.result == "success"){
					location.href="/user/following_list_view";
				}else{
					alert(data.errorMessage);
				}
			},
			error: function(e){
				alert("프로필을 편집하는데 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	}); */
});
</script>