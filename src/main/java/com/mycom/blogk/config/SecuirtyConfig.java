package com.mycom.blogk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mycom.blogk.config.auth.PrincipalDetailService;



// 시큐리티 3개 셋트 아노테이션
@Configuration //빈등록 (loc 관리)
@EnableWebSecurity // 필터
@EnableGlobalMethodSecurity(prePostEnabled = true)// 특정 주소로 접근 하면 권한 인증을 미리 체크
public class SecuirtyConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean //즉 리턴값을 스프링이 관리 하는 IoC가 된다
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	//가로챈 password 가 무엇으로 해쉬가 되어 회원가입 되었는지 알아야 같은 해쉬로 암호화 해서 DB에 있는 
	//해쉬와 비교 할 수 있게 선언 - confure(Authentication~~)을 오버라이딩 해준다
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());//null 자리 만들어야 
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	       .csrf().disable()//csrf 토큰 비활성화
	       .authorizeRequests()
	         .antMatchers("/","/auth/**", "/js/**","/css/**","/image/**")
	         .permitAll()
	         .anyRequest()
	         .authenticated()
	     .and()
	         .formLogin()
	         .loginPage("/auth/loginForm")
	         .loginProcessingUrl("/auth/loginProc")
	         .defaultSuccessUrl("/"); // 스프링 시큐리티가 해당 로그인을 가로채서 controller 에 따로 경로를 mapping 하지 않는다
	    
		
	}

}
