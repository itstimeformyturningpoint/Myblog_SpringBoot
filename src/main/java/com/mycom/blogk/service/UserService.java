package com.mycom.blogk.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blogk.model.RoleType;
import com.mycom.blogk.model.User;
import com.mycom.blogk.repository.UserRepository;

@Service // 스프링이 component를 통해서 스캔을 통해 bean에 등록
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encode;
	
	// 연결 후 서비스 함수 만들기 --insert
	@Transactional // 이 애노테이션으로 묶여서 실패가 되면 rollback 이 됨
	public void 회원가입(User user) {
		
		String rawPassword = user.getPassword();
		String encPassword = encode.encode(rawPassword);
		user.setPassword(encPassword);
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
			
		 
		
	}
	
}
