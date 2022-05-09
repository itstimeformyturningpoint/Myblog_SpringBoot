package com.mycom.blogk.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mycom.blogk.service.BoardService;



@Controller //viewResolver 가 작동한다
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"","/"})
	public String index(Model model, @PageableDefault(size=3, sort="id", direction=Sort.Direction.DESC) Pageable pageable ) {
		// 인덱스로 갈때 데이터를 가져가야 하는데 모델이 필요하다
	    model.addAttribute("boards",boardService.글목록(pageable));
		return"index"; 
		//viewResolver 작동! 인덱스페이지로 model 에 담은 데이타 가지고 인덱스로 가면 items 에서 받아서 뿌려준다
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/detail";
	}
	@GetMapping("/board/{id}/updateForm")//글수정하기
	public String updateForm(@PathVariable int id,Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "/board/updateForm";
		
	}
// 글쓰기 USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
		
	}
}
