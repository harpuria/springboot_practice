package com.yhh.springboot_exam.config.auth.dto;

import com.yhh.springboot_exam.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

// 세션에 사용자 정보를 저장하기 위한 DTO
// 직렬화(Serializable) 인터페이스를 구현해야 한다.
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
