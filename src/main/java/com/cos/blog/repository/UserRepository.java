package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO (In JSP)
// 자동으로 bean등록이 된다. bean 객체 생성 -> DI 가능
// @Repository 생략이 가능하다
public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	

}
