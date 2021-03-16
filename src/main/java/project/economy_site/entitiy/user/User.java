package project.economy_site.entitiy.user;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import project.economy_site.entitiy.base.DateTimeEntity;
import project.economy_site.entitiy.post.Post;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends DateTimeEntity {

    @Id
    @GeneratedValue
    private Long userId;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    private String salt;

    @OneToMany(fetch = LAZY, mappedBy = "user")
    private List<Post> postList = new ArrayList<>();

    public void clearPassword() {
        this.password = null;
    }

    public void createAndSetPassword(String rawPassword) {
        this.salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(rawPassword, this.salt);
    }

    public void mappingPost(Post post) {
        this.postList.add(post);
        post.mappingUser(this);
    }
}
