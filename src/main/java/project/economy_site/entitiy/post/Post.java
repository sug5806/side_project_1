package project.economy_site.entitiy.post;

import lombok.*;
import project.economy_site.entitiy.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {
    @Id
    @GeneratedValue
    private Long postId;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

    @Builder.Default
    private Long viewCount = 0L;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void addViewCount() {
        this.viewCount = this.getViewCount() + 1;
    }

    public void changeContents(String contents) {
        this.contents = contents;
    }

}
