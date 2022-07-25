<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="d-flex justify-content-center">
	<div class="contents-box">
		<c:if test="${not empty userId}">
		<div class="write-box border rounded m-3">
			<textarea id="writeTextArea" class="w-100 border-0" placeholder="내용을 입력해주세요"></textarea>
			<div class="d-flex justify-content-between">
				<div class="d-flex">
					<input type="file" id="file" class="d-none" accept=".jpg,.jpeg,.gif,.png">
					<a href="#" id="fileUploadBtn">
						<img src="https://cdn-icons-png.flaticon.com/512/44/44289.png" width="35" class="m-1">
					</a>
					
					<!-- 업로드 된 이미지 파일명을 띄움 -->
					<div id="fileName"></div>
				</div>
				<button type="button" id="uploadBtn" class="btn btn-info m-1">게시</button>
			</div>
		</div>
		</c:if>
		
		<c:forEach var="post" items="${postList}">
			<div class="card m-2">
				<div class="d-flex justify-content-between">
					<strong class="m-2">닉네임(user id: ${post.userId})</strong>
					<c:if test="${post.userId eq userId}">
						<img src="/static/img/more_icon.png" width="30" class="moreIcon m-2"/>
					</c:if>
				</div>
				
				<img src="${post.imagePath}" class="uploadedImg w-100">
				
				<div class="d-flex align-items-center">
					<img src="/static/img/empty-heart.png" class="like_heart m-2" width="30"/>
					<strong>좋아요</strong>&nbsp;11개
				</div>
				
				<div class="m-2">${post.content}</div><hr>
				
				<strong class="m-2">댓글</strong>
				
				<div class="d-flex justify-content-between">
					<div class="comments ml-4">
						<strong>id: </strong>댓글내용
					</div>
					
					<%-- 댓글 삭제 --%>
					<a href="#" class="commentDelBtn">
						<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px" class="mr-3">
					</a>
				</div>
				
				<%-- 댓글 달기 --%>
				<c:if test="${not empty userId}">
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light" data-post-id="${post.id}">게시</button><!-- 'data-' + any name (but no Upper case) -->
					</div>
				</c:if>
			</div>
		</c:forEach>
	</div>
</div>

<script>
$(document).ready(function(){
	// 파일업로드 이미지 클릭 => input type="file" 숨어있던 창이 열림
	$('#fileUploadBtn').on('click',function(e){
		e.preventDefault();		// a 태그를 클릭했을 때 창이 위로 올라가는 것을 방지
		$('#file').click();		// input file을 클릭한 것과 같은 효과
	});
	
	// 파일 업로드를 했을 때, 파일 이름 노출, 파일 확장자 검증
	$('#file').on('change',function(e){		// 태그의 변화 감지 (이미지가 선택됐을 때)
		let fileName = e.target.files[0].name;	// ex) image.jpg
		let arr = fileName.split(".");
		
		// 확장자 검증
		if(arr.length < 2 || 
				(arr[arr.length-1] != 'gif'
						&& arr[arr.length-1] != 'jpg'
						&& arr[arr.length-1] != 'jpeg'
						&& arr[arr.length-1] != 'png')){
			alert("이미지 파일만 업로드 가능합니다.");
			$(this).val("");			// 서버에 잘못된 파일이 올라가기 전에 파일을 비움
			$('#fileName').text("");	// 파일 이름 뜨는 것도 지워줌
			return;
		}
		
		// 임시파일명 노출
		$('#fileName').text(fileName);
	});
	
	$('#uploadBtn').on('click',function(){
		// validation checking
		let fileName = $('#fileName').text();	// ex) image.jpg
		if(fileName == ''){
			alert("포스팅할 사진을 선택하세요.");
			return;
		}
		
		let content = $('#writeTextArea').val();
		
		// form 태그를 자바스크립트에서 만든다.
		let formData = new FormData();
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]);	// $('#file')[0]: 첫 번째 input file 태그, .files[0]: 업로드 된 첫 번째 파일
		
		// AJAX form 데이터 전송
		$.ajax({
			// request
			type: "POST"
			,url: "/post/create"
			,data: formData
			,encType: "multipart/form-data"	// file 업로드 필수 설정
			,processData: false				// file 업로드 필수 설정 (이미지 파일이므로 data를 string으로 변환하지 않하게 함)
			,contentType: false				// file 업로드 필수 설정
			
			// response
			,success: function(data){
				if(data.result == "success"){
					alert("포스팅 완료.");
					location.reload();
				} else{
					alert(data.errorMessage);
				}
			}
			,error: function(e){
				alert("포스팅 실패");
			}
		});
	});
	
	// 댓글 게시 버튼 클릭
	$('.comment-btn').on('click',function(e){
		let postId = $(this).data('post-id');	// 'data-' 뒤에 'post-id'
		
		// $(this).parent().children(".");		// 부모 태그 이용해서 댓글 내용 가져오기
		let content = $(this).siblings("input").val().trim();	// 형제(sibling) 태그 이용해서 댓글 내용 가져오기
		
		$.ajax({
			// request
			type: "POST"
			,url: "/comment/create"
			,data: {"postId":postId,"content":content}
			
			// response
			,success: function(data){
				if(data.result == "success"){
					
					location.reload();
				} else{
					alert(data.errorMessage);
				}
			}
			,error: function(e){
				alert("댓글 게시 실패");
			}
		});
	});
});
</script>