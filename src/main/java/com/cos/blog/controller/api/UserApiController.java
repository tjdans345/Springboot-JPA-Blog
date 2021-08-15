package com.cos.blog.controller.api;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	// 2번째 방법 (세션 영역 저장) DI
//	@Autowired
//	private HttpSession session;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) { //username, password, email
		System.out.println("UserApiController: save 호출됨");
		// 실제로 DB에 insert를 하고 아래에서 return이 되면 되요.
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴(JACKSON)
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user, 
			@AuthenticationPrincipal PrincipalDetail principal, HttpSession session) { // key = value, x-www-form-urlencoded 
		userService.회원수정(user);
		// 여기서는 트랜잭션이 종료되개 때문에 DB에 값은 변경이 됐음
		// 하지만 세션값은 변경되지 않은 상태이기 때문에 우리가 직접 세션값을 변경해줄 것임.
		Authentication authentication = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		
	}
	
	// 스프링 시큐리티 이용전 전통적인 로그인 방식!!!!!
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user
//									// HttpSession session 1번째 방법 (세션 영역 저장) 매개변수에 적어도 자동으로 DI해준다.
//										) {
//		System.out.println("UserApiController: login 호출됨");
//		// 실제로 DB에 insert를 하고 아래에서 return이 되면 되요.
//		User principal = userService.로그인(user); // principal(접근 주체)
//		
//		ModelAndView mav = new ModelAndView();
//		
//		if(principal != null) {
//			// 세션영역에 로그인 정보를 저장 시킴
//			session.setAttribute("principal", principal);
//		}
//		
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 자바오브젝트를 JSON으로 변환해서 리턴(JACKSON)
//	}
	
	
	
	

}