package project.economy_site.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.economy_site.dto.post.PostRequestDTO;
import project.economy_site.dto.post.PostResponseDTO;
import project.economy_site.entitiy.Post;
import project.economy_site.repository.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Post createPost(PostRequestDTO postRequestDTO) {
        Post post = Post.builder()
                .title(postRequestDTO.getTitle())
                .contents(postRequestDTO.getContents())
                .build();

        return postRepository.save(post);
    }

    public List<PostResponseDTO> getPosts() {
        List<Post> allPost = postRepository.findAll();

        Stream<Post> postStream = allPost.stream();

        Stream<PostResponseDTO> dtoStream = postStream.map(post -> PostResponseDTO.builder()
                .id(post.getPostId())
                .title(post.getTitle())
                .contents(post.getContents())
                .build()
        );

        return dtoStream.collect(Collectors.toList());
    }

    public PostResponseDTO getPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.orElseThrow();

        return PostResponseDTO.builder()
                .id(post.getPostId())
                .title(post.getTitle())
                .contents(post.getContents())
                .createdAt(post.getCreatedAt().toString())
                .build();

    }

    @Transactional
    public Long updatePost(Long id, PostRequestDTO postRequestDTO) {

        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.orElseThrow();

        post.changeTitle(postRequestDTO.getTitle());
        post.changeContents(postRequestDTO.getContents());

        return post.getPostId();
    }
}
