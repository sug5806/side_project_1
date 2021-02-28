package project.economy_site.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import project.economy_site.dto.post.PostRequestDTO;
import project.economy_site.entitiy.Post;
import project.economy_site.service.PostService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@WebMvcTest(PostController.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostService postService;

    @Test
    @DisplayName("게시물 생성 컨트롤러 테스트")
    public void createPostTest() throws Exception {
        // given
        PostRequestDTO postRequestDTO = PostRequestDTO.builder()
                .title("test title")
                .contents("test contents")
                .build();


        // when
        Post createdPost = postService.createPost(postRequestDTO);

        // then
        assertThat(postRequestDTO.getTitle()).isEqualTo(createdPost.getTitle());
        assertThat(postRequestDTO.getContents()).isEqualTo(createdPost.getContents());


    }

    @Test
    @DisplayName("게시물 조회 컨트롤러 테스트")
    public void getPostTest() throws Exception {
        // given

        // then
    }
}