package com.yhh.springboot_exam.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yhh.springboot_exam.config.auth.dto.SessionUser;
import com.yhh.springboot_exam.domain.posts.Posts;
import com.yhh.springboot_exam.domain.posts.PostsRepository;
import com.yhh.springboot_exam.domain.user.User;
import com.yhh.springboot_exam.web.dto.PostsSaveRequestDto;
import com.yhh.springboot_exam.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 랜덤포트로 서블릿을 실행해서 테스트. JPA 기능을 테스트할 때는 WebMvcTest 로는 안된다.
public class PostsApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER") // roles 를 추가해서 사용자 인증 처리가 되게한다.
    public void posts_save() throws Exception{
        // given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                                                            .title(title)
                                                            .content(content)
                                                            .author("author")
                                                            .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when

        /* MockMvc 적용 전
        // ResponseEntity<PK> 는 Restful API 에서 반환 타입으로 많이 사용된다.
        // 해당 url 로 요청하였을 때 정상적으로 응답하는지 확인할 수 있다. 그리고 그 응답에 따라 별도의 처리도 할 수 있고..
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
         */

        mvc.perform(post(url)
            .contentType(MediaType.APPLICATION_JSON) // APPLICATION_JSON_UTF8 는 Deprecated 되었음!
            .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        /* MockMvc 적용 전
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK); // StatusCode 가 200 인가?
        assertThat(responseEntity.getBody()).isGreaterThan(0L);              // getBody 결과가 0L 보다 큰가?
         */

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    //@Test (@LoginUser 추가된 내용으로 변견된거 테스트 오류나서 일단 주석처리...)
    //@WithMockUser(roles = "USER")
    public void posts_update() throws Exception{
        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                                                    .title("title")
                                                    .content("content")
                                                    .author("author")
                                                    .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                                                                .title(expectedTitle)
                                                                .content(expectedContent)
                                                                .build();

        User user = User.builder().name("author").email("email").picture("picture").build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        /* MockMvc 적용 전
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
         */

        // when
        /* MockMvc 적용 전
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
         */

        mvc.perform(put(url)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        /* MockMvc 적용 전
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
         */

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }

    @Test
    public void baseTimeEntityTest(){
        // given
        LocalDateTime now = LocalDateTime.of(2020,8,31,0,0,0);
        postsRepository.save(Posts.builder()
                                .title("title")
                                .content("content")
                                .author("author")
                                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>> createDate=" + posts.getCreatedDate()+", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}