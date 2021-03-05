package project.economy_site.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.economy_site.dto.post.PostRequestDTO;
import project.economy_site.dto.post.PostResponseDTO;
import project.economy_site.entitiy.post.Post;
import project.economy_site.repository.PostRepository;

import java.time.format.DateTimeFormatter;
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
        List<Post> allPost = postRepository.findAll(Sort.by(Sort.Direction.DESC, "postId"));

        Stream<Post> postStream = allPost.stream();

        Stream<PostResponseDTO> dtoStream = postStream.map(post -> PostResponseDTO.builder()
                .id(post.getPostId())
                .title(post.getTitle())
                .contents(post.getContents())
                .createdAt(post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .viewCount(post.getViewCount())
                .build()
        );

        return dtoStream.collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDTO getPost(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        Post post = optionalPost.orElseThrow();

        post.addViewCount();

        return PostResponseDTO.builder()
                .id(post.getPostId())
                .title(post.getTitle())
                .contents(post.getContents())
                .createdAt(post.getCreatedAt().toString())
                .viewCount(post.getViewCount())
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

    @Transactional
    public void delete(Long id) {
        Optional<Post> findPost = postRepository.findById(id);
        Post post = findPost.orElseThrow();
        postRepository.delete(post);
    }
}
