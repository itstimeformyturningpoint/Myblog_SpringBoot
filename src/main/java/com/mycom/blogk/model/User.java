package com.mycom.blogk.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import org.hibernate.annotations.CreationTimestamp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity  //User클래스가 MySQL에 자동으로 테이블이 형성
//@DynamicInsert// insert 시 null값 없애주기
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트를 따라가겠다
	int id; //시퀀스, auto-incerement
	
	@Column(nullable = false, length = 30)
	private String username;//아이디
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
 //DB는 RoleType 이 없어서 해당 변수가 String이라고 알려준다
	//@ColumDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType role;  //나중에 Enum을 쓸것
	
	@CreationTimestamp
	private Timestamp createDate;
	
	
}
