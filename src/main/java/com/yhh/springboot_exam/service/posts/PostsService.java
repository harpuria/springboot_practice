package com.yhh.springboot_exam.service.posts;

import com.yhh.springboot_exam.config.auth.LoginUser;
import com.yhh.springboot_exam.config.auth.dto.SessionUser;
import com.yhh.springboot_exam.domain.posts.Posts;
import com.yhh.springboot_exam.domain.posts.PostsRepository;
import com.yhh.springboot_exam.web.dto.PostsListResponseDto;
import com.yhh.springboot_exam.web.dto.PostsResponseDto;
import com.yhh.springboot_exam.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // 인자있는 생성자를 생성해서 생성자 주입이 가능하게 한다.
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsSaveRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                        .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));

        // 쿼리를 날리지 않고도 Entity 클래스인 Posts 객체의 값을 바꿔주는것만으로도 update 가 이루어진다.
        // 이를 JPA 의 영속성 컨텍스트라고 한다.
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다 id=" + id));

        postsRepository.delete(posts);
    }
}
