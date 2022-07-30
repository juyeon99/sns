<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="h-100 d-flex justify-content-between align-items-center">
	<div class="logo">
		<a href="/timeline/timeline_view"><h1 style="color:white;">Instagram</h1></a>
	</div>
	<div class="login-info ml-4">
		<!-- 로그인이 되었을 때만(세션에 값이 있을 때만) 보이도록 -->
		<c:if test="${not empty userId}">
			<div class="d-flex">
				<!-- <a href="/user/profile_view"><img src="/static/img/pfp.png" class="m-2 pfp-cropper"/></a> -->
				<div class="img-wrapper2 mr-2">
					<a href="/user/profile_view"><img src="${profileImagePath}"/></a>
				</div>
				<span class="text-white">${userName}님 안녕하세요!</span>&nbsp;&nbsp;
				<a href="/user/sign_out">로그아웃</a>
			</div>
		</c:if>
		<c:if test="${empty userId}">
			<a href="/user/sign_in_view">로그인</a>
		</c:if>
	</div>
</div>