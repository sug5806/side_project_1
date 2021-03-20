package project.economy_site.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import project.economy_site.dto.post.PostRequestDTO;
import project.economy_site.dto.post.PostResponseDTO;
import project.economy_site.entitiy.post.Post;
import project.economy_site.service.PostService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<PostResponseDTO> posts = postService.getPosts();
        model.addAttribute("posts", posts);
        return "/post/post_list";
    }

    @PostMapping("/post")
//    @PreAuthorize("hasAnyAuthority('ROLE_USER')")
    public RedirectView postCreate(@Valid PostRequestDTO postRequestDTO) {
        Post createdPost = postService.createPost(postRequestDTO);
        String url = String.format("/post/%d", createdPost.getPostId());
        return new RedirectView(url);
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model) {
        PostResponseDTO post = postService.getPost(id);
        model.addAttribute("post", post);
        return "post/detail";
    }

    @PutMapping("/post/{id}")
    public RedirectView postUpdate(@PathVariable Long id,
                                   @Valid PostRequestDTO postRequestDTO) {
        Long postId = postService.updatePost(id, postRequestDTO);
        String url = String.format("/post/%s", postId.toString());
        return new RedirectView(url);
    }

    @DeleteMapping("/post/{id}")
    public RedirectView postDelete(@PathVariable Long id) {
        postService.delete(id);
        return new RedirectView("/posts");
    }
}
