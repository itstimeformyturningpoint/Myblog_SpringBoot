package com.mycom.blogk.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mycom.blogk.model.User;


import lombok.Getter;


@Getter //board 가 user 정보를 쓸 수 있도록 
public class PrincipalDetail implements UserDetails { // 타입을 정해주는 implements
	
	@Autowired
	private User user; // 객체를 품는 콤포지션

	//PrincipalDetail 클래스에 User는 null 이기 때문에 여기에 생성자를 만든다
	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료되지 않았는지 리턴 (true 만료안됨)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		//비번만료?
		return true;
	}

	@Override
	public boolean isEnabled() {
		// 계정활성화??
		return true;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 계정권한리턴-타입이 특이하다 
		Collection<GrantedAuthority> collectors = new ArrayList<>();		 
		
		// 자바는 이렇게 메서드를 이런식으로 넣을 수가 없다 그래서 람다식으로 진행
		/*collectors.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
			
				return"ROLE_"+user.getRole();
			}		
		});
		*/
		collectors.add(()->{return "ROLE_"+user.getRole();});
		return collectors;// 권한이 여러개이면 포문 돌려야 하는데 지금은 권한이 하나니까
	}
	

}
