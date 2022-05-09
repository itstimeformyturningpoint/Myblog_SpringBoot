package com.mycom.blogk.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blogk.model.RoleType;
import com.mycom.blogk.model.User;
import com.mycom.blogk.repository.UserRepository;

@RestController //응답만 해줄 수 있게 
public class DummyControllerTest {
	
	@Autowired //메모리에 떠주게 하는 의존성 주입
	private UserRepository userRepository; 

	//  한페이지당 2건식 받아볼 예정 
	//http://localhost:8000/blogk/dummy/user?page=2
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault (size=2, sort ="id", direction = Sort.Direction.DESC)Pageable pageable){
		//Pageable(data domain)
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
		
	}
	
	//http://localhost:8000/blogk/dummy/user/3
	@GetMapping("/dummy/user/{id}")
  public User detail(@PathVariable int id) {
		
// 람다식 		User user = userRepository.findById(id).orElseThrow(()->{
		//               return new IllegalArgumentException("해당 사용자는 없습니다"+id); 
	 //                       });  
  User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
	// findBy의 리턴 타입이 옵션날 
	  //옵션날로 User객체를 감싸서 가져올때 null 인지 아닌지 판단해서 달라는 뜻
	  
@Override
public IllegalArgumentException get() {

		
return new IllegalArgumentException("해당 사용자는 없습니다"+id); 
  }	  
});  
  
  // user객체는 자바 오브젝트 , 요청을 webbrower, 그런데 웹브라우저는 user 를 이해하지 못한다
  //변환시켜주기(json) 
  //스프링부트 = MessageConverter 자동응답, user 오브젝트를 jason으로 변환시켜준다
  //그래서 web 상에서 jason 형태의 결과물을 내준다
  return user;
  }
	
	@PostMapping("/dummy/join")
	//http://localhost:8000/blogk/dummy/join
	public String Join(User user) {
		System.out.println("username:"+ user.getUsername());
		System.out.println("password:"+ user.getPassword());
		System.out.println("email:"+ user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원 가압이 완료 되었습니다.";
		
	}
	

}
