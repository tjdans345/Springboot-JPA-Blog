package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. Ioc를 해준다.
// 비지니스로직은 서비스단에서 작성한다.
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user) {
		
		String rawPassword = user.getPassword(); //1234 원문 패스워드
		String encPassword = encoder.encode(rawPassword); // 패스워드 암호화
		
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		
		userRepository.save(user); // insert할때는 save를 사용하네
	}

//	@Transactional(readOnly = true) //Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지 시킴)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	
	
}
