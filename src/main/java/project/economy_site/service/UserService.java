package project.economy_site.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.economy_site.dto.user.SignUpDTO;
import project.economy_site.entitiy.user.User;
import project.economy_site.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    public User authenticate(String email, String password) {
        Optional<User> findUser = userRepository.findByEmail(email);
        User user = findUser.orElseThrow();

        boolean checkup = BCrypt.checkpw(password, user.getPassword());
        if (!checkup) {
            throw new IllegalArgumentException("유효하지 않은 비밀번호 입니다.");
        }

        return user;
    }

    @Transactional
    public void signUp(SignUpDTO signUpDTO) throws IllegalArgumentException {
        User user = User.builder()
                .email(signUpDTO.getEmail())
                .build();

        user.createAndSetPassword(signUpDTO.getPassword());
        userRepository.save(user);
    }
}
