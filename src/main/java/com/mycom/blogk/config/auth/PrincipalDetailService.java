package com.mycom.blogk.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycom.blogk.model.User;
import com.mycom.blogk.repository.UserRepository;
@Service
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌때 변수 2개(username,password)처리시 password는 자동처리 하지만 
			//username은 DB에 있는지 확인해주어야 함
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	// 이 함수가 제대로 overriding 되어야 쓰고자 하는 user를 담아서 세션에 넣고 로그인 처리를 할 수있다 	
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{//optional 타입은 orElseThrow
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다:"+username);
				});
		return new PrincipalDetail(principal);// 시큐리티 세션에 유저 정보가 저장되고 타입은 UserDetails
	}

}
