package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO (In JSP)
// 자동으로 bean등록이 된다. bean 객체 생성 -> DI 가능
// @Repository 생략이 가능하다 JpaRepository 상속 받았기 때문
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	

}

//로그인을 위한 함수
	// JPA Naming 전략(쿼리) / 대문자를 기준으로 동작
	// select * from user where username = ?1 AND password = ?2;
//	User findByUsernameAndPassword(String username, String password);
	
	// 2번째 방법 (네이티브 쿼리)
//	@Query(value = "select * from user where username = ?1 AND password = ?2", nativeQuery = true)
//	User login(String username, String password);