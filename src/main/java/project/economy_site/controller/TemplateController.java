package project.economy_site.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.economy_site.dto.post.PostRequestDTO;
import project.economy_site.dto.post.PostResponseDTO;
import project.economy_site.service.PostService;

@Controller
@RequiredArgsConstructor
public class TemplateController {
    private final PostService postService;

    @GetMapping("/post")
    public String postTextEditor(Model model) {
        model.addAttribute("post", PostRequestDTO.builder().build());
        return "/post/create";
    }

    @GetMapping("/post/{id}/edit")
    public String editPost(@PathVariable Long id, Model model) {
        PostResponseDTO post = postService.getPost(id);
        model.addAttribute("post", post);
        return "/post/modify";
    }
}
