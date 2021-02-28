package project.economy_site.dto.post;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class PostRequestDTO {
    @NotEmpty
    private String title;
    @NotEmpty
    private String contents;
}
