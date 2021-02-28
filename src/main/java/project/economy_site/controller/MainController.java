package project.economy_site.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.economy_site.dto.post.PostResponseDTO;
import project.economy_site.service.PostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final PostService postService;

    @GetMapping("/")
    public String main(Model model) {
        List<PostResponseDTO> posts = postService.getPosts();

        model.addAttribute("posts", posts);
        return "main";
    }
}
