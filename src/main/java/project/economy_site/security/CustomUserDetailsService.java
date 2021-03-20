package project.economy_site.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.economy_site.entitiy.user.User;
import project.economy_site.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User findUser = userRepository.findByEmail(email);

//        User findUser = byEmail.orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않습니다."));

        return CustomUserDetails.builder()
                .email(findUser.getEmail())
                .password(findUser.getPassword())
                .authority(findUser.getRole())
                .enabled(true)
                .build();
    }
}
