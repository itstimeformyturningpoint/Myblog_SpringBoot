package com.mycom.blogk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mycom.blogk.model.User;


//DAO역할
// Ioc 에서 객체를 가지고 있나? 자동으로 빈으로 등록된다 
//@Repository 생략가능 
public interface UserRepository extends JpaRepository<User, Integer> {

	// 로그인 가로채서 username 체그하기 위한 함수 
	//SELECT * FROM user WHERE username =1?;
	Optional<User> findByUsername(String username);
}
