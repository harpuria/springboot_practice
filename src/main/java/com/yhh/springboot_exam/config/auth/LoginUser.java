package com.yhh.springboot_exam.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)      // 어노테이션이 생성될 수 있는 위치 지정 (여기서는 메소드의 파라미터로 선언된 객체에 적용가능)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
