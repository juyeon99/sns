<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="h-100 d-flex flex-column justify-content-center align-items-center">
	<form method="post" action="/user/login_view">
		<input type="text" id="id" name="id" class="form-control" placeholder="id" style="width:300px; margin-top:150px;">
		<input type="password" id="password" name="password" class="form-control" placeholder="password" style="width:300px; margin-top:10px;">
		<button type="submit" class="btn btn-info login-btn" style="width:300px; margin-top:10px;">로그인</button>
	</form>
	<a href="sign_up_view"><input type="button" class="btn btn-secondary" style="width:300px; margin-top:10px;" value="회원가입"></a>
</div>