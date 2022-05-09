package com.mycom.blogk.test;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor // 생성자

public class Member {
	private int id;
	private String username;
	private String password;
	private String emial;
	

}
