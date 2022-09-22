package project.trut.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotEmpty;

@Setter @Getter
@RequiredArgsConstructor
public class MemberUpdateDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
}
