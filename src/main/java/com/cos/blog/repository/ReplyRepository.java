package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	
	// 인터페이스 안에서는 public을 생략해도 상관없다.
	@Modifying
	@Query(value = "INSERT INTO reply (userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)
	int mySave(int userId, int boardId, String content); //JDBC insert, update시 업데이트된 행의 개수를 리턴해준다.
	

}
