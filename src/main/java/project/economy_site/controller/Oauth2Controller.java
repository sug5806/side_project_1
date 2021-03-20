package project.economy_site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.economy_site.annotation.SocialUser;
import project.economy_site.entitiy.user.User;

@Controller
public class Oauth2Controller {

    @GetMapping("/auth/kakao/callback")
    @ResponseBody
    public String kakaoCallback(String code) {
        System.out.println("코드는 : " + code);
        return "코드는 : " + code;
    }

    @GetMapping("/socialLoginSuccess")
    public String loginComplete(@SocialUser User user) {
        return "redirect:/posts";
    }
}
