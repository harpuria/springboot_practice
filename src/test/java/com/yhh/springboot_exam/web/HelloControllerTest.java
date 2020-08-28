package com.yhh.springboot_exam.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


// JUnit5 가 나왔지만, 여전히 JUnit4 를 많이 사용한다.
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class) // 슬라이스 테스트. 테스트 대상이 될 컨트롤러 지정.
public class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello() throws Exception{
        String hello = "hello";

        // perform() 은 요청을 하는 메소드이다
        // andExpert() 는 요청에 대한 결과를 검증한다
        // andDo() 는 요청에 대한 처리를 한다 (보통 print() 를 넣어 전체 결과를 확인할 수 있게한다)
       mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello))
                .andDo(print());
    }

    @Test
    public void helloDto() throws Exception{
        String name = "hello";
        int amount = 1000;

        // get().param() 파라미터명, 값을 인자로 전달할 수 있다. 주의점은 값도 String 형식으로 전달해야 한다는 점!
        mockMvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // jsonpath() 는 json 응답값을 필드별로 검증할 수 있게 한다. $.필드명 을 적어줘야 한다.
                .andExpect(jsonPath("$.amount", is(amount)))
                .andDo(print());
    }
}