package com.mycom.blogk.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blogk.config.auth.PrincipalDetail;
import com.mycom.blogk.dto.ResponseDto;
import com.mycom.blogk.model.Board;
import com.mycom.blogk.service.BoardService;



@RestController
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	
	
	// 글쓰기
	@PostMapping("/api/board")
	public ResponseDto<Integer>save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
		boardService.글쓰기(board,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		
	}
	@DeleteMapping("/api/board/{id}")
	public ResponseDto<Integer>deleteById(@PathVariable int id){
		boardService.글삭제하기(id);
		System.out.println("글삭제하기"+id);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	@PutMapping("/api/board/{id}")
	public ResponseDto<Integer>update(@PathVariable int id, @RequestBody Board board){
		/*System.out.println("BoardApiController : update:id"+id);
		System.out.println("BoardApiController : update:board"+board.getTitle());
		System.out.println("BoardApiController : update:board"+board.getContent());
		*/
	    boardService.글수정하기(id,board);
	    return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
		
	}
}
