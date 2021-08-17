<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

	<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	<c:if test="${board.user.id == principa.user.id}">
		<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<br /> <br />
	<div>
		글번호: <span id="id"><i>${board.id} </i></span> 작성자: <span><i>${board.user.username} </i></span>
	</div>
	<br />
	<div class="form-group">
		<h3>${board.title}</h3>
	</div>
	<hr>
	<div class="form-group">
		<div>${board.content}</div>
	</div>


	<div class="card">
		<div class="card-body">
			<textarea class="form-control" rows="2" cols=""></textarea>
		</div>
		<div class="card-footer">
			<button type="button" class="btn btn-primary">등록</button>
		</div>
	</div>
	<br />
	<div class="card">
		<div class="card-header">댓글 리스트</div>
	</div>
	<ul id="comment--box" class="list-group">
		<li id="comment--1" class="list-group-item d-flex justify-content-between">
			<div>댓글내용</div>
			<div class="d-flex">
				<div class="font-italic">작성자 : ㄴㅇㅁㄴㅇ &nbsp;</div>
				<button class="badge" type="button">삭제</button>
			</div>
		</li>
	</ul>


	<hr>
</div>


<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>


