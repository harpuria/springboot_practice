package com.yhh.springboot_exam.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// DB 에 접근하게 해주는 Repository 인터페이스. (MyBatis 에서 DAO 라고 불리는 DB Layer 와 비슷하다)
// JpaRepository<Entity, PK Type> 를 상속하면 기본적인 CRUD 메소드가 자동 생성된다.
public interface PostsRepository extends JpaRepository<Posts, Long> {
    // JPA 에서 제공하지 않는 메소드는 아래와 같이 직접 쿼리문을 작성하여 생성할 수 있다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
