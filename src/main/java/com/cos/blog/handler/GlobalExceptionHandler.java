package com.cos.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 모든 exception 발생 시 이 클래스로 들어옴.
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value=Exception.class) // Exception은 모든 Exception의 부모라서 모든 Exception은 이 메소드를 탐.
    public String handleArgumentException(Exception e){
        return "<h1>" + e.getMessage()+"</h1>";


    }
}
