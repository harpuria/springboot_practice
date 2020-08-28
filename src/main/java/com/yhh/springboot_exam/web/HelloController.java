package com.yhh.springboot_exam.web;

import com.yhh.springboot_exam.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloResponseDto(@RequestParam("name") String name, @RequestParam("amount") int amount){
        // @RestController 을 붙였을 경우 객체를 반환할 때 Jackson 라이브러리에서 알아서 JSON 으로 변환시켜 준다.
        return new HelloResponseDto(name, amount);
    }
}
