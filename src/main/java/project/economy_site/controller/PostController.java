package project.economy_site.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import project.economy_site.dto.post.PostRequestDTO;
import project.economy_site.dto.post.PostResponseDTO;
import project.economy_site.service.PostService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/post")
    public String postTextEditor(Model model) {
        model.addAttribute("postRequestDTO", PostRequestDTO.builder().build());
        return "post/postEditor";
    }

    @PostMapping("/post")
    public String postCreate(@Valid PostRequestDTO postRequestDTO) {
        postService.createPost(postRequestDTO);
        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        PostResponseDTO post = postService.getPost(id);
        model.addAttribute("post", post);
        return "post/detail";
    }

    @PutMapping("/post/{id}")
    public String postUpdate(@PathVariable Long id,
                             @Valid PostRequestDTO postRequestDTO) {
        Long postId = postService.updatePost(id, postRequestDTO);
        String url = "post/" + postId.toString();
        return "redirect:/" + url;
    }

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<PostResponseDTO> posts = postService.getPosts();
        model.addAllAttributes(posts);
        return "post/posts";
    }

}
