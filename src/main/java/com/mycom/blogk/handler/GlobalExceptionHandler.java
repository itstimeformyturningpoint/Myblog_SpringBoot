package com.mycom.blogk.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.blogk.dto.ResponseDto;

@ControllerAdvice//모든 exception이 발생하면 여기로 호게 
@RestController
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value=Exception.class)
	public ResponseDto<String> handleArgumentException(IllegalArgumentException e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
	

		//에러내용을 너무 디테일하게 표시 해 줄 수 없으므로 중간에 낚아채서 메세지만
	}

}
