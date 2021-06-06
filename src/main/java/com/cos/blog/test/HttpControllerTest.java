package com.cos.blog.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답 (HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data를 응답 해줌) RestController이다

@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest :";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+"getter"+m.getUsername());
		m.setUsername("홍길동");
		System.out.println(TAG+"getter"+m.getUsername());
		return "lombok test 완료";
	}
	
	
	// 인터넷 브라우저 요청은 get요청밖에 할 수 없다.
	// http://localhost:8090/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		
		System.out.println(TAG + "getter: " + m.getId());
		m.setId(5000);
		System.out.println(TAG + "setter:" + m.getId());
		return "get 요청"+m.getId() +", "+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	// http://localhost:8090/http/post (insert)
	@PostMapping("/http/post") // text/plain(raw Data) , application/json(raw Data)
	public String postTest(@RequestBody Member m) { //MessageConverter가 알아서 매핑을 해준다 (스프링부트)
		return "post 요청:" + m.getId() +", "+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}

	// http://localhost:8090/http/put (update)
	@PutMapping("/http/put")
	public String putTset(@RequestBody Member m) {
		return "put 요청" + m.getId() +", "+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	// http://localhost:8090/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}

}
