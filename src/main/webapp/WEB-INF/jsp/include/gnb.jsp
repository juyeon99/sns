<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="h-100 d-flex justify-content-between align-items-center">
	<div class="logo">
		<h1>Instagram</h1>
	</div>
	<div class="login-info ml-4">
		<!-- 로그인이 되었을 때만(세션에 값이 있을 때만) 보이도록 -->
		<c:if test="${not empty userId}">
			<div>
				<span class="text-white">${userName}님 안녕하세요!</span>
				<a href="/user/sign_out">로그아웃</a>
			</div>
		</c:if>
		<c:if test="${empty userId}">
			<a href="/user/sign_in_view">로그인</a>
		</c:if>
	</div>
</div>