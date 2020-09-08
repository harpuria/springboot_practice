package com.yhh.springboot_exam.config.auth;

import com.yhh.springboot_exam.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

// LoginUser 어노테이션을 사용하기 위한 클래스.
// 메소드 파라미터에 들어가는 어노테이션이기 때문에 HandlerMethodArgumentResolver 를 구현해줘야 한다.
@RequiredArgsConstructor
@Component
public class LoginuserArgumentResolver implements HandlerMethodArgumentResolver {
    
    private final HttpSession httpSession;
    
    // 컨트롤러 메소드의 특정 파라미터 지원여부 판단
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginuserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null; // LoginUser 어노테이션이 붙어있으면 true
        boolean isUserClass = SessionUser.class.equals(parameter.getParameterType()); // 파라미터 클래스타입이 SessionUser 이면 true
        
        return isLoginuserAnnotation && isUserClass; // 그 결과값을 반환
    }

    // 파라미터에 전달할 객체 생성
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
