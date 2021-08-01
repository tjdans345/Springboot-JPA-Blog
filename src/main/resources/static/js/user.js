let index = {
	init: function() {
		$("#btn-save").on("click", () => { // function(){} 대신에 ()=>{} 사용이유 : this를 바인딩 하기 위해서!!
			this.save();
		});
//		$("#btn-login").on("click", () => { // function(){} 대신에 ()=>{} 사용이유 : this를 바인딩 하기 위해서!!
//			this.login();
//		});
	},
	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		};

		//$.ajax().done().fail()
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!
		// ajax호출 시 default가 비동기 호출
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환 해줌
		$.ajax({ // 회원가입 수행 요청 (100초가 걸린다고 가정해도 밑에 로직이 실행이 됨)
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http body 데이터
			contentType: "application/json; charset=urf-8", //body데이터가 어떤 타입인지(MIME)
			dataType: "json" // 응답타입:json / 요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경해줌 
		}).done(function(response) {
			console.log(response);
			alert("회원가입이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error) {
			alert(JSON.stringify(error));
		});
	},

//	login: function() {
//		let data = {
//			username: $("#username").val(),
//			password: $("#password").val(),
//			email: $("#email").val(),
//		};
//
//		//$.ajax().done().fail()
//		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!
//		// ajax호출 시 default가 비동기 호출
//		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환 해줌
//		$.ajax({ // 회원가입 수행 요청 (100초가 걸린다고 가정해도 밑에 로직이 실행이 됨)
//			type: "POST",
//			url: "/api/user/login",
//			data: JSON.stringify(data), // http body 데이터
//			contentType: "application/json; charset=urf-8", //body데이터가 어떤 타입인지(MIME)
//			dataType: "json" // 응답타입:json / 요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경해줌 
//		}).done(function(response) {
//			console.log(response);
//			alert("로그인이 완료되었습니다.");
//			location.href = "/";
//		}).fail(function(error) {
//			alert(JSON.stringify(error));
//		});
//	}

}

index.init();