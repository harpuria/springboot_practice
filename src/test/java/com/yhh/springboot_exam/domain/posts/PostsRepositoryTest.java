package com.yhh.springboot_exam.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After // 단위 테스트가 끝난 뒤에 실행되는 메소드를 지정
    public void cleanup(){
        postsRepository.deleteAll(); // deleteAll() 은 DB내 모든 데이터를 삭제한다.
    }

    @Test
    public void saveNfindall(){
        // given
        String title = "게시글 제목";
        String content = "게시글 본문";

        // save() 는 insert or update 를 수행. (id 가 있으면 update, 없으면 insert)
        // builder() 로 객체를 생성 (lombok 에서 @Builder 를 붙여서 가능)
        postsRepository.save(Posts.builder()
                        .title(title)
                        .content(content)
                        .author("hong")
                        .build());

        // when
        List<Posts> postsList = postsRepository.findAll(); // findAll() 은 모든 데이터를 가져온다.

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}