<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="shadow-box m-5">
		<div class="d-flex justify-content-center m-5">
			<%-- 키보드 Enter키로 로그인이 될 수 있도록 form 태그를 만들어준다.(submit 타입의 버튼이 동작됨) --%>
			
			<form id="loginForm" action="/user/sign_in" method="post">
				<%-- input-group-prepend: input box 앞에 ID 부분을 회색으로 붙인다. --%>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">ID</span>
					</div>
					<input type="text" class="form-control" name="loginId">
				</div>
				
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text">PW</span>
					</div>
					<input type="password" class="form-control" name="password">
				</div>
				
				<%-- btn-block: 로그인 박스 영역에 버튼을 가득 채운다. --%>
				<a class="btn btn-block btn-dark" href="/user/sign_up_view">회원가입</a>
				<input type="submit" class="btn btn-block btn-info" value="로그인">
			</form>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	$('#loginForm').on('submit',function(e){
		e.preventDefault();
		let loginId = $('input[name=loginId]').val().trim();
		if (loginId == "") {
			alert("아이디를 입력하세요.");
			return false;
		}
		
		let password = $('input[name=password]').val();
		if (password == "") {
			alert("비밀번호를 입력하세요");
			return false;
		}
		
		// 서버에 전송
		let url = $(this).attr("action"); 	// form태그에 있는 action값을 가져옴
		let params = $(this).serialize(); 	// form태그에 들어있는 name 속성 값들을 한번에 가져옴
		
		$.post(url, params)
		.done(function(data) {
			if (data.result == "success") {
				alert("로그인 되었습니다.");
				location.href="/timeline/timeline_view";
			} else {
				alert(data.errorMessage);
			}
		});
	});
});
</script>