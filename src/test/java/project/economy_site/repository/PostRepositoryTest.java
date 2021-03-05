package project.economy_site.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.economy_site.entitiy.post.Post;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    @DisplayName("포스트 데이터 생성")
    public void postSaveTest() throws Exception {
        // given
        Post post = Post.builder()
                .title("test post title2")
                .contents("test post contents2")
                .build();

        // when
        Post savePost = postRepository.save(post);

        // then
        assertThat(post.getTitle()).isEqualTo(savePost.getTitle());
        assertThat(post.getContents()).isEqualTo(savePost.getContents());
    }

    @Test
    @DisplayName("포스트 데이터 삭제")
    public void postDeleteTest() throws Exception {
        // given
        Post post = Post.builder()
                .postId(1L)
                .title("test title")
                .contents("test contents")
                .build();

        Post savePost = postRepository.save(post);

        // when
        postRepository.delete(savePost);
        // then
        assertThat(postRepository.findAll()).isEmpty();
    }
}