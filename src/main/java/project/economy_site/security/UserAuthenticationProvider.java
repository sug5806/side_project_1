package project.economy_site.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import project.economy_site.dto.user.UserDTO;
import project.economy_site.entitiy.user.User;
import project.economy_site.service.UserService;

import java.util.ArrayList;
import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();

        try {
            User user = userService.authenticate(email, password);
            if (user == null)
                throw new BadCredentialsException("Login Error !!");
            user.clearPassword();

            UserDTO userDTO = UserDTO.builder()
                    .email(user.getEmail())
                    .build();

            ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new UsernamePasswordAuthenticationToken(userDTO, null, authorities);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
