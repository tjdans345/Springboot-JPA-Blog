package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplysaveRequestDto {
	
	//DTO를 사용했을 때 장점은 필요한 DATA를 한번에 받을 수 있다.
	
	private int userId;
	private int boardId;
	private String content;

}
