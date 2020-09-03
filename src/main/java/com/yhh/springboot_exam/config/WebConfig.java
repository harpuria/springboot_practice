package com.yhh.springboot_exam.config;

import com.yhh.springboot_exam.config.auth.LoginuserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginuserArgumentResolver loginuserArgumentResolver;

    // HandlerMethodArgumentResolver 를 구현한 클래스는 항상 아래 메소드를 통해 추가해주어야 한다
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginuserArgumentResolver);
    }
}
