package project.economy_site.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.economy_site.dto.user.SignUpDTO;
import project.economy_site.entitiy.user.User;
import project.economy_site.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public User checkPassword(String email, String password) {
        User findUser = userRepository.findByEmail(email);

//        User findUser = byEmail.orElseThrow(() -> new NoSuchElementException("해당 유저가 없습니다."));

        boolean checkup = BCrypt.checkpw(password, findUser.getPassword());
        if (!checkup) {
            throw new IllegalArgumentException("유효하지 않은 비밀번호 입니다.");
        }

        return findUser;
    }

    @Transactional
    public void signUp(SignUpDTO signUpDTO) throws IllegalArgumentException {
        User user = User.builder()
                .email(signUpDTO.getEmail())
                .role("ROLE_USER")
                .build();

        user.createAndSetPassword(signUpDTO.getPassword());
        userRepository.save(user);
    }
}
