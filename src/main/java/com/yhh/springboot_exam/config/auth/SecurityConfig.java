package com.yhh.springboot_exam.config.auth;

import com.yhh.springboot_exam.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable() // h2-console 를 활용하기 위해서 disable 처리
            .and()
                .authorizeRequests() // URL 별 권한 관리를 설정하는 옵션의 시작점
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() // antmatchers() 권한 관리 대상 지정 옵션
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // permitAll() 은 전체 열람 권한, hasRole() 은 특정 사람만 권한
                .anyRequest() // 설정된 값들 이외 나머지 URL 들을 의미
                .authenticated() // 인증된 사용자들에게 나머지 URL 들을 허용한다는 의미
            .and()
                .logout().logoutSuccessUrl("/") // 로그아웃 성공시 리다이렉트 되는 url
            .and()
                .oauth2Login() // Oauth2 로그인 기능에 대한 설정의 시작점
                .userInfoEndpoint() // Oauth2 로그인 성공 이후 사용자 정보를 가져올 때 설정들
                .userService(customOAuth2UserService); // 소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스 구현체 등록.

    }
}
