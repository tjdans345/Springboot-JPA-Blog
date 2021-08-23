package com.cos.blog.model;



import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id;
	
	@Column(nullable = false, length= 100)
	private String title;
	
	@Lob //대용량 데이터를 사용할 때 Lob을 사용
	private String content;
	
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = one 한명의 유저는 여러개의 게시글을 쓸 수 있다. default는 EAGER전략이다(ManyToOne)
	@JoinColumn(name="userId")
	private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // mappedBy 연관관계의 주인이 아니다(난 FK가 아니에요) DB에 칼럼을 만들지 마세요. default는 LAZY전략이다(OneToMany)
	@JsonIgnoreProperties({"board"}) //무한 참조 방지
	@OrderBy("id desc")
	private List<Reply> replys; //EAGER 한번에 다 같이 들고온다, LAZY 필요할 때 들고온다.
	
	@CreationTimestamp
	private Timestamp createDate;
	
}


