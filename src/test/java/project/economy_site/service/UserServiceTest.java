package project.economy_site.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.economy_site.dto.user.UserDTO;
import project.economy_site.entitiy.user.User;
import project.economy_site.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void 비밀번호_검사() throws Exception {
        // given
        UserDTO loginUser = loginUser();
        User user = createUser();
        given(userRepository.findByEmail(loginUser.getEmail())).willReturn(user);

        // when
        User check = userService.checkPassword(loginUser().getEmail(), loginUser.getPassword());

        // then
        assertThat(loginUser.getEmail()).isEqualTo(check.getEmail());
    }

    private User createUser() {
        return User.builder()
                .email("sug5806@naver.com")
                .password("$2a$10$SiUQGfMAkiBEUOJJ5.pvveaC2uiO9o9/WNF6SrzU9YR.GvnW3b49S")
                .role("ROLE_USER")
                .build();
    }

    private UserDTO loginUser() {
        return UserDTO.builder()
                .email("sug5806@naver.com")
                .password("1234")
                .build();
    }

}