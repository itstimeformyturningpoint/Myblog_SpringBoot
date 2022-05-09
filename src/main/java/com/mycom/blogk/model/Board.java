package com.mycom.blogk.model;

import java.sql.Timestamp;
import java.util.List;

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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Board {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이타
	private String content;
	

	private int count;
	
	@ManyToOne (fetch = FetchType.EAGER) //many = board , one = user 즉 한명이 여러개의 게시글을 쓸수 있다 
	@JoinColumn(name="userId")
	private User user;
	
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER)
	//연관관계의 주인이 아니다(FK가 아니다) DB의 컬럼만들지 마.
	private List<Reply> reply;
	
	
	@CreationTimestamp
	private Timestamp createDate;


	
	
	

}
