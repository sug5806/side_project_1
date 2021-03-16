package project.economy_site.dto.user;

import lombok.*;
import project.economy_site.entitiy.base.DateTimeEntity;

import javax.validation.constraints.NotEmpty;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SignUpDTO extends DateTimeEntity {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}
