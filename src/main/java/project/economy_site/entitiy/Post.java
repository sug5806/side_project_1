package project.economy_site.entitiy;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {
    @Id
    @GeneratedValue
    private Long postId;

    @NotEmpty
    @NonNull
    private String title;
    @NotEmpty
    @NonNull
    private String contents;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContents(String contents) {
        this.contents = contents;
    }

}