package com.yhh.springboot_exam.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Optional 타입은 메소드가 반환할 값이 '없음' 을 명확하게 표현할 필요가 있으며
    // null 을 반환하면 에러를 유발할 가능성이 높은 상황에서 쓰는 타입이라고 한다. .. 좀 더 공부해야겠다.
    Optional<User> findByEmail(String email);
}
