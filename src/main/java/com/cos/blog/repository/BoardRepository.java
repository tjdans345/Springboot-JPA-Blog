package com.cos.blog.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;

// DAO (In JSP)
// 자동으로 bean등록이 된다. bean 객체 생성 -> DI 가능
// @Repository 생략이 가능하다 JpaRepository 상속 받았기 때문
public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	
	

}

