package project.economy_site.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.economy_site.dto.user.SignUpDTO;
import project.economy_site.dto.user.UserDTO;
import project.economy_site.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/signup/form")
    public String singUpForm(Model model) {
        model.addAttribute("signupDTO", SignUpDTO.builder().build());
        return "/sign_up/signup";
    }

    @PostMapping("/signup")
    public String signUp(SignUpDTO signUpDTO, Model model) {
        try {
            userService.signUp(signUpDTO);
        } catch (Exception e) {
            model.addAttribute("hasError", true);
            return "/sign_up/signup";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginForm", UserDTO.builder().build());
        return "login/form";
    }
}
