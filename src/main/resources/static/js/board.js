let index = {
	init: function() {
		$("#btn-save").on("click", () => { // function(){} 대신에 ()=>{} 사용이유 : this를 바인딩 하기 위해서!!
			this.save();
		});
		
		$("#btn-delete").on("click", () => { // function(){} 대신에 ()=>{} 사용이유 : this를 바인딩 하기 위해서!!
			this.deleteById();
		});
		
		$("#btn-update").on("click", () => { // function(){} 대신에 ()=>{} 사용이유 : this를 바인딩 하기 위해서!!
			this.update();
		});
		
		$("#btn-reply-save").on("click", () => { // function(){} 대신에 ()=>{} 사용이유 : this를 바인딩 하기 위해서!!
			this.replySave();
		});
	},
	
	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};

		//$.ajax().done().fail()
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!
		// ajax호출 시 default가 비동기 호출
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환 해줌
		$.ajax({ // 회원가입 수행 요청 (100초가 걸린다고 가정해도 밑에 로직이 실행이 됨)
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=urf-8", 
			dataType: "json" 
		}).done(function(response) {
			console.log(response);
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	update: function() {
		let id = $("#id").val();
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		};

		//$.ajax().done().fail()
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!
		// ajax호출 시 default가 비동기 호출
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환 해줌
		$.ajax({ // 회원가입 수행 요청 (100초가 걸린다고 가정해도 밑에 로직이 실행이 됨)
			type: "PUT",
			url: "/api/board/"+id,
			data: JSON.stringify(data), 
			contentType: "application/json; charset=urf-8", 
			dataType: "json" 
		}).done(function(response) {
			console.log(response);
			alert("글 수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	deleteById: function() {
		
		let id = $("#id").text();

		$.ajax({ 
			type: "DELETE",
			url: "/api/board/"+id,
			contentType: "application/json; charset=urf-8", 
			dataType: "json" 
		}).done(function(response) {
			console.log(response);
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	replySave: function() {
		let data = {
			userId: $("#userId").val(),
			boardId: $("#boardId").val(),
			content: $("#reply-content").val(),
		};
		console.log("콘솔"+data.userId);

		//$.ajax().done().fail()
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!
		// ajax호출 시 default가 비동기 호출
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환 해줌
		$.ajax({ // 회원가입 수행 요청 (100초가 걸린다고 가정해도 밑에 로직이 실행이 됨)
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data), 
			contentType: "application/json; charset=urf-8", 
			dataType: "json" 
		}).done(function(response) { 
			console.log(response);
			alert("댓글작성이 완료되었습니다.");
			location.href = `/board/${data.boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},
	
	replyDelete: function(boardId, replyId) {
		
		$.ajax({ // 회원가입 수행 요청 (100초가 걸린다고 가정해도 밑에 로직이 실행이 됨)
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			dataType: "json"
		}).done(function(response) { 
			alert("댓글 삭제가 완료되었습니다.");
			location.href = `/board/${boardId}`;
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

}

index.init();