package com.yhh.springboot_exam.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

// 컨트롤러를 테스트하는 것이 아니기 때문에 굳이 어노테이션을 붙일일도 없다.
public class HelloResponseDtoTest {
    @Test
    public void lombokTest(){
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        // JUnit 에 있는 assertThat 쓰지 말자. (deprecated 되어서 안쓰는게 좋음. assertJ 꺼 쓰자)
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}