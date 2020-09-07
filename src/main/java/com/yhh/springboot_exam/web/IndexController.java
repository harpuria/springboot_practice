package com.yhh.springboot_exam.web;

import com.yhh.springboot_exam.config.auth.LoginUser;
import com.yhh.springboot_exam.config.auth.dto.SessionUser;
import com.yhh.springboot_exam.service.posts.PostsService;
import com.yhh.springboot_exam.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

// Mustache View 를 반환하기 위해서는 @RestController 가 아닌 @Controller 를 붙여주어야 한다.
@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostsService poststService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", poststService.findAllDesc());

        // @LoginUser 를 만들었으므로 아래 코드는 불필요하다.
        //SessionUser user = (SessionUser)httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        return "index"; // src/main/resources/templates/index.mustache 를 찾아서 View Resolver 가 처리해준다.
    }

    @GetMapping("/posts/save")
    public String postsSave(@LoginUser SessionUser user, Model model){

        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model, @LoginUser SessionUser user){
        PostsResponseDto dto = poststService.findById(id);
        model.addAttribute("post", dto);

        if(user != null){
            model.addAttribute("userName", user.getName());
        }

        return "posts-update";
    }
}
