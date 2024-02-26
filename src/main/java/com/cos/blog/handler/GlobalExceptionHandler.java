package com.cos.blog.handler;

import com.cos.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 exception 발생 시 이 클래스로 들어옴.
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class) // Exception은 모든 Exception의 부모라서 모든 Exception은 이 메소드를 탐.
    public ResponseDto<String> handleArgumentException(Exception e){
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage());
    }
}
