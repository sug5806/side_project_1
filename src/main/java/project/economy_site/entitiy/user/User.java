package project.economy_site.entitiy.user;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
import project.economy_site.entitiy.base.DateTimeEntity;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends DateTimeEntity {

    @Id
    @GeneratedValue
    private Long userId;

    private String email;

    @Column(length = 100)
    private String name;

    private String password;

    @Column(length = 100)
    @Enumerated(value = EnumType.STRING)
    private SocialType socialType;

    private String role;

    private String principal;

//    @OneToMany(fetch = LAZY, mappedBy = "user")
//    private List<Post> postList = new ArrayList<>();

    public void clearPassword() {
        this.password = null;
    }

    public void createAndSetPassword(String rawPassword) {
        this.password = BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

//    public void mappingPost(Post post) {
//        this.postList.add(post);
//        post.mappingUser(this);
//    }
}
