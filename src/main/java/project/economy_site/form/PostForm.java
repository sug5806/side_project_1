package project.economy_site.form;

import javax.validation.constraints.NotEmpty;

public class PostForm {
    @NotEmpty(message = "회원 이름은 필수 입니다")
    private String name;
}
