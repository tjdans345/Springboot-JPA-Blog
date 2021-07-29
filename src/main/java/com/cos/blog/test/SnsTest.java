package com.cos.blog.test;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SnsTest {
	
	@RequestMapping(value= "/kakaologin", produces = "apllication/json", method = RequestMethod.GET )
	public String snsTest(@RequestParam("code")String code, RedirectAttributes ra, HttpSession session,
						  HttpServletResponse response
			) throws IOException {

		String test = "";
		System.out.println("왔업");
		System.out.println(code);
		
		return "kakao";
	}

}
