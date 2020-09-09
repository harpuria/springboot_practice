package com.yhh.springboot_exam.web;

import com.yhh.springboot_exam.config.auth.LoginUser;
import com.yhh.springboot_exam.config.auth.dto.SessionUser;
import com.yhh.springboot_exam.service.posts.PostsService;
import com.yhh.springboot_exam.web.dto.PostsListResponseDto;
import com.yhh.springboot_exam.web.dto.PostsResponseDto;
import com.yhh.springboot_exam.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsSaveRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

    // 모든 리스트 가져오기 api (테스트 작성)
    @GetMapping("/api/v1/posts/all")
    public List<PostsListResponseDto> all(){
        return postsService.findAllDesc();
    }
}
