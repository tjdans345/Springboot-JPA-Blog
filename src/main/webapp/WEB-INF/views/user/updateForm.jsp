<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<input type="hidden" id="id" value="${principa.user.id }"/>
		<div>
			<label for="username">Username</label> 
			<input type="text" value="${principa.user.username}" class="form-control" placeholder="Enter username" id="username" readonly="readonly">
		</div>
		
		<div class="form-group">
			<label for="password">Password</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<div class="form-group">
			<label for="email">Email</label> 
			<input type="email" value="${principa.user.email}" class="form-control" placeholder="Enter email" id="email">
		</div>
		
	</form>
	<button type="button" id="btn-update" class="btn btn-primary">회원수정 완료</button>
	
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>