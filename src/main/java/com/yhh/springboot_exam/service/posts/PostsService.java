package com.yhh.springboot_exam.service.posts;

import com.yhh.springboot_exam.domain.posts.PostsRepository;
import com.yhh.springboot_exam.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor // 인자있는 생성자를 생성해서 생성자 주입이 가능하게 한다.
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
