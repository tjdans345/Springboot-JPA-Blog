package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	// http://localhost:8090/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본경로 : src/main/resources/static
		// 리터면 : /home.html
		// 풀경로 : src/main/resources/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		System.out.println("왔엉");
		return "/a.PNG";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
//	    prefix: /WEB-INF/views/
//	    suffix: .jsp
		System.out.println("왔엉");
		return "test";
	}
}
