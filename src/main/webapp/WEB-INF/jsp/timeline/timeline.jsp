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
					<a href="#self" id="fileUploadBtn">
						<img src="https://cdn-icons-png.flaticon.com/512/44/44289.png" width="35" class="m-1">
					</a>
					
					<!-- 업로드 된 이미지 파일명을 띄움 -->
					<div id="fileName"></div>
				</div>
				<button type="button" id="uploadBtn" class="btn btn-info m-1">게시</button>
			</div>
		</div>
		</c:if>
		
		<c:forEach var="card" items="${cardList}">
			<div class="card m-2">
				<div class="d-flex justify-content-between">
					<strong class="m-2">${card.user.name}</strong>
					<c:if test="${card.post.userId eq userId}">
						<a href="#" class="more-btn" data-toggle="modal" data-target="#moreModal" data-post-id="${card.post.id}">
							<img src="/static/img/more_icon.png" width="30" class="moreIcon m-2"/>
						</a>
					</c:if>
				</div>
				
				<img src="${card.post.imagePath}" class="uploadedImg w-100">
				
				<div class="d-flex align-items-center">
					<c:if test="${card.filledLike}">
						<img src="/static/img/full-heart.png" class="like_heart m-2" width="30" data-post-id="${card.post.id}"/>
					</c:if>
					<c:if test="${!card.filledLike}">
						<img src="/static/img/empty-heart.png" class="like_heart m-2" width="30" data-post-id="${card.post.id}"/>
					</c:if>
					<strong>좋아요</strong>&nbsp;${card.likeCount}개
				</div>
				
				<div class="m-2">${card.post.content}</div>
				
				<strong class="p-2 border-top">댓글</strong>
				
				<c:forEach var="commentView" items="${card.commentViewList}">
					<div class="d-flex justify-content-between">
						<div class="ml-4">
							<strong>${commentView.user.name}: </strong>${commentView.comment.content}
						</div>
						
						<%-- 댓글 삭제 --%>
						<c:if test="${userId eq commentView.comment.userId}">
							<a href="#" class="commentDelBtn" data-comment-id="${commentView.comment.id}">
								<img src="https://www.iconninja.com/files/603/22/506/x-icon.png" width="10px" height="10px" class="mr-3">
							</a>
						</c:if>
					</div>
				</c:forEach>
				
				<%-- 댓글 달기 --%>
				<c:if test="${not empty userId}">
					<div class="comment-write d-flex border-top mt-2">
						<input type="text" class="form-control border-0 mr-2 comment-text" placeholder="댓글 달기"/> 
						<button type="button" class="comment-btn btn btn-light" data-post-id="${card.post.id}">게시</button><!-- 'data-' + any name (but no Upper case) -->
					</div>
				</c:if>
			</div>
		</c:forEach>
	</div>
</div>


<!-- Modal -->
<div class="modal fade" id="moreModal">
	<div class="modal-dialog modal-sm modal-dialog-centered">
    	<div class="modal-content">
    		<!-- modal창 안에 내용 넣기 -->
    		<div class="text-center py-3">
    			<!-- d-block: 클릭할 수 있는 영역 넓힘 -->
    			<a href="#" class="del-post d-block">삭제하기</a>
    		</div>
    		<div class="text-center py-3 border-top">
    			<!-- data-dissmiss: modal창 닫힘 -->
    			<a href="#" class="d-block" data-dismiss="modal">취소</a>
    		</div>
    	</div>
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
				(arr[arr.length-1].toLowerCase() != 'gif'
						&& arr[arr.length-1].toLowerCase() != 'jpg'
						&& arr[arr.length-1].toLowerCase() != 'jpeg'
						&& arr[arr.length-1].toLowerCase() != 'png')){
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
		
		if(content.length < 1){
			alert("댓글 내용을 입력하세요.");
			return;
		}
		
		$.ajax({
			// request
			type: "POST"
			,url: "/comment/create"
			,data: {"postId":postId,"content":content}
			
			// response
			,success: function(data){
				if(data.result == "success"){
					location.reload(true);		// 댓글 쓰고 나서 새로고침
				} else{
					alert(data.errorMesssage);
				}
			}
			,error: function(e){
				alert("댓글 게시 실패");
			}
		});
	});
	
	$('.commentDelBtn').on('click',function(e){
		let id = $(this).data('comment-id');	// 'data-' 뒤에 'comment-id'
		
		$.ajax({
			// request
			type: "DELETE"
			,url: "/comment/delete"
			,data: {"id":id}
			
			// response
			,success: function(data){
				if(data.result == "success"){
					location.reload(true);
				}
			}
			,error: function(e){
				alert("댓글 삭제 실패");
			}
		});
	});
	
	$('.like_heart').on('click',function(e){
		let postId = $(this).data('post-id');
		
		$.ajax({
			// request
			type: "POST"
			,url: "/like/" + postId
			//,data: {"postId":postId}
			
			// response
			,success: function(data){
				if(data.result == "success"){
					location.reload(true);
				} else{
					alert(data.errorMesssage);
				}
			}
			,error: function(e){
				alert("좋아요 실패");
			}
		});
	});
	
	// ... 더보기 버튼 클릭시, 모달에 삭제될 post id를 넣어준다.
	$('.more-btn').on('click',function(e){
		// e.preventDefault();		// a 태그 기본동작(스크롤 업) 중단	= #self
		
		let postId = $(this).data('post-id');
		
		// modal에 삭제될 글 번호를 심어줌 (modal은 재활용 되기 때문)
		$('#moreModal').data('post-id', postId);	// adding <data-post-id = postId>
	});
	
	// modal창 안에 있는 삭제하기 버튼 클릭
	$('#moreModal .del-post').on('click',function(e){
		e.preventDefault();
		
		let postId = $('#moreModal').data('post-id');
		
		
		// 서버 삭제 요청
		$.ajax({
			type:"DELETE",
			url: "/post/delete",
			data:{"postId":postId},
			success: function(data){
				if(data.result == "success"){
					location.reload(true);
				}else{
					alert(data.errorMessage);
				}
			},
			error: function(e){
				alert("삭제하는데 실패했습니다. 관리자에게 문의해주세요.");
			}
		});
	});
});
</script>