<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="sign-up-box">
		<h1 class="m-4">회원가입</h1>
		<form id="signUpForm" method="post" action="/user/sign_up">
			<span class="sign-up-subject">ID</span>
			<%-- 인풋 옆에 중복확인 버튼을 옆에 붙이기 위해 div 만들고 d-flex --%>
			<div class="d-flex ml-3 mt-3">
				<input type="text" name="loginId" class="form-control col-6" placeholder="ID를 입력해주세요">
				<button type="button" id="loginIdCheckBtn" class="btn btn-success ml-1">중복확인</button>
			</div>
			
			<%-- 아이디 체크 결과 --%>
			<div class="ml-3 mb-3">
				<div id="idCheckLength" class="small text-danger d-none">ID를 4자 이상 입력해주세요.</div>
				<div id="idCheckDuplicated" class="small text-danger d-none">이미 사용중인 ID입니다.</div>
				<div id="idCheckOk" class="small text-success d-none">사용 가능한 ID 입니다.</div>
			</div>
			
			<span class="sign-up-subject">Password</span>
			<div class="m-3">
				<input type="password" name="password" class="form-control col-6" placeholder="비밀번호를 입력하세요">
			</div>

			<span class="sign-up-subject">Confirm password</span>
			<div class="m-3">
				<input type="password" name="confirmPassword" class="form-control col-6" placeholder="비밀번호를 입력하세요">
			</div>

			<span class="sign-up-subject">Name</span>
			<div class="m-3">
				<input type="text" name="name" class="form-control col-6" placeholder="이름을 입력하세요">
			</div>

			<span class="sign-up-subject">e-mail</span>
			<div class="m-3">
				<input type="text" name="email" class="form-control col-6" placeholder="이메일을 입력하세요">
			</div>
			
			<br>
			<div class="d-flex justify-content-center m-3">
				<button type="submit" id="signUpBtn" class="btn btn-info">가입하기</button>
			</div>
		</form>
	</div>
</div>

<script>
$(document).ready(function(){
	$('#loginIdCheckBtn').on('click',function(){
		$('#idCheckLength').addClass('d-none');
		$('#idCheckDuplicated').addClass('d-none');
		$('#idCheckOk').addClass('d-none');
		
		let loginId = $('input[name=loginId]').val().trim();
		if(loginId.length < 4){
			$('#idCheckLength').removeClass("d-none");
			return;
		}
		
		$.ajax({
			url:"/user/is_duplicated_id"
			,data:{"loginId":loginId}
			,success:function(data){
				if(data.result){
					$('#idCheckDuplicated').removeClass("d-none");
				} else if(!data.result){
					$('#idCheckOk').removeClass("d-none");
				} else{
					alert("아이디 중복 체크 실패");
				}
			}
			,error:function(e){
				alert("아이디 중복 체크에 실패 하였습니다.")
			}
		});
	});
	
	$('#signUpForm').on('submit',function(e){
		e.preventDefault();
		let loginId = $('input[name=loginId]').val().trim();
		if (loginId == "") {
			alert("아이디를 입력하세요.");
			return false;
		}
		
		let password = $('input[name=password]').val();
		let confirmPassword = $('input[name=confirmPassword]').val();
		if (password == "" || confirmPassword == "") {
			alert("비밀번호를 입력하세요");
			return false;
		}
		
		if (password != confirmPassword) {
			alert("비밀번호가 일치하지 않습니다.");
			$('#password').val("");
			$('#confirmPassword').val("");
			return false;
		}
		
		let name = $('input[name=name]').val().trim();
		if (name == "") {
			alert("이름을 입력하세요");
			return false;
		}
		
		let email = $('input[name=email]').val().trim();
		if (email == "") {
			alert("이메일을 입력하세요");
			return false;
		}
		
		if ($('#idCheckOk').hasClass('d-none')) {
			alert("아이디 중복확인을 해주세요.");
			return false;
		}
		
		// 서버에 전송
		let url = $(this).attr("action"); 	// form태그에 있는 action값을 가져옴
		let params = $(this).serialize(); 	// form태그에 들어있는 name 속성 값들을 한번에 가져옴
		
		$.post(url, params)
		.done(function(data) {
			if (data.result == "success") {
				alert("회원가입을 환영합니다. 로그인을 해주세요.");
				location.href = "/user/sign_in_view";
			} else {
				alert("회원가입에 실패했습니다. 다시 시도해주세요.");
			}
		});
	});
});
</script>