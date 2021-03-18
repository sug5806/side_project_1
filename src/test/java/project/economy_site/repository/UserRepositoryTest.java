package project.economy_site.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import project.economy_site.entitiy.user.User;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        User user1 = User.builder()
                .email("sug5806@gmail.com")
                .password("$2a$10$etaT.Zry7uVGvD8N0jmxi.2D25vxt3qB8sv9JgD5PSGSDWimxnzN.")
                .role(new SimpleGrantedAuthority("ROLE_USER"))
                .build();

        User user2 = User.builder()
                .email("sug5806@naver.com")
                .password("$2a$10$SiUQGfMAkiBEUOJJ5.pvveaC2uiO9o9/WNF6SrzU9YR.GvnW3b49S")
                .role(new SimpleGrantedAuthority("ROLE_USER"))
                .build();

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    @DisplayName("email로 유저 검색")
    public void findUserByEmail() throws Exception {
        // given
        String email = "sug5806@naver.com";

        // when
        Optional<User> optionalUser = userRepository.findByEmail(email);

        User user = optionalUser.orElseThrow(() -> new NoSuchElementException("not found user"));

        // then
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
    }

}