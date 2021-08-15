<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username</label> 
			<input type="text" class="form-control" name="username" placeholder="Enter username" id="username">
		</div>

		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" class="form-control" name="password" placeholder="Enter password" id="password">
		</div>

<!-- 		<div class="form-group form-check"> -->
<!-- 			<label class="form-check-label">  -->
<!-- 			<input class="form-check-input" name="remember" type="checkbox"> Remember me -->
<!-- 			</label> -->
<!-- 		</div> -->

		<button type="submit" id="btn-login" class="btn btn-primary">로그인</button>
		<a href="https://kauth.kakao.com/oauth/authorize?client_id=98291c71ce14217c087b4b5471461f9a&redirect_uri=http://localhost:8090/auth/kakao/callback&response_type=code"><img height="38px" src="/image/kakao_login_button.png"></a>
	</form>


</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>


