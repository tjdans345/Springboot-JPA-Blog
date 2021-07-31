let index = {	
	
	init: function() {
		$("#btn-save").on("click", ()=> { // function(){} 대신에 ()=>{} 사용이유 : this를 바인딩 하기 위해서!!
			this.save();
		});
	},
	
	save: function() {
//		alert("user의 save함수 호출됨");
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val(),
		}
		
		//$.ajax().done().fail()
		
		$.ajax().done().fail(); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청!!!
	}
	
}

index.init();