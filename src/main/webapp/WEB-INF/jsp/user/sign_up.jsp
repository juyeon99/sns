<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="h-100 d-flex justify-content-center align-items-center flex-column mt-4">
	<form method="post" action="/user/login_view">
		<div class="form-group">
			<label for="id">ID</label> 
			<div class="d-flex">
				<input type="text" id="id" name="id" class="form-control" placeholder="아이디를 입력해주세요">
				<input type="button" value="중복확인" class="btn ml-2 btn-info">
			</div>
		</div>

		<div class="form-group">
			<label for="password">password</label> 
			<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력해주세요">
		</div>
		
		<div class="form-group">
			<label for="confirm-password">confirm password</label> 
			<input type="password" id="confirm-password" name="confirm-password" class="form-control" placeholder="비밀번호 확인">
		</div>

		<div class="form-group">
			<label for="name">이름</label> 
			<input type="text" id="name" name="name" class="form-control" placeholder="이름을 입력해주세요">
		</div>

		<div class="form-group">
			<label for="email">이메일</label> 
			<input type="text" id="email" name="email" class="form-control" placeholder="이메일을 입력해주세요">
		</div>

		<input type="submit" class="btn btn-info" value="회원가입">
	</form>
</div>