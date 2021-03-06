package com.yhh.springboot_exam.domain.posts;

import com.yhh.springboot_exam.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// Entity class 에서는 Setter 를 만들지 않는것을 권장한다.
// Setter 가 없는 대신, 생성자에 최종 값을 채운 뒤 DB 에 삽입(INSERT)할 수 있다.
@Getter
@NoArgsConstructor // 기본 생성자 자동 생성 어노테이션
@Entity // 실제 DB 테이블과 매칭이 될 클래스(Entity class)에 이 어노테이션을 붙인다.
public class Posts extends BaseTimeEntity { // 생성 및 수정시간 등록하는 BaseTimeEntity 상속
    // @Id 는 PK 를 의미
    // GenerationType.IDENTITY 는 auto_increment 를 의미
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    // lombok 에서 제공하는 빌더 패턴을 만들어주는 어노테이션
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
