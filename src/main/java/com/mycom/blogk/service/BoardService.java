package com.mycom.blogk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycom.blogk.dto.ResponseDto;
import com.mycom.blogk.model.Board;
import com.mycom.blogk.model.RoleType;
import com.mycom.blogk.model.User;
import com.mycom.blogk.repository.BoardRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	
	
	// 연결 후 서비스 함수 만들기 --insert
	@Transactional // 이 애노테이션으로 묶여서 실패가 되면 rollback 이 됨
	public void 글쓰기(Board board, User user) {// title, content 조회수는 강제로 넣어줘야. user 정보
		
        board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
			
	}
	@Transactional(readOnly = true)//select 만 하는거니까
	public Page<Board>글목록(Pageable pageable){
		return boardRepository.findAll(pageable);
	}
	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 상세 보기 실패: 아이디를 찾을 수 없습니다");
				});
	}
	@Transactional
	 public void 글삭제하기(int id) {
		System.out.println("글삭제하기:"+id);
		 boardRepository.deleteById(id);
	 }
	@Transactional
	 public void 글수정하기 (int id,Board requestBoard) {
		 Board board = boardRepository.findById(id)
				 .orElseThrow(()->{
						return new IllegalArgumentException("글 찾기 실패: 아이디를 찾을 수 없습니다");
					});//영속화 완료 
		 board.setTitle(requestBoard.getTitle());//setTilte 을 getTitle 로 교체
		 board.setContent(requestBoard.getContent());
		 // 해당 함수 즉 서비스가 종료될 때 트랜잭션 종료, 
		 //이때 더티체킹-자동 업데이트가 db쪽으로 flush(커밋_그러자면 transactional타켓 필요)
		 
	 }
}
