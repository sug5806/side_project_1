package project.economy_site.dto.post;

import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostResponseDTO {
    private Long id;
    private String title;
    private String contents;
    private String createdAt;
    private Long viewCount;
}
