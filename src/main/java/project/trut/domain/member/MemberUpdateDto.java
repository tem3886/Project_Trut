package project.trut.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter @Getter
public class MemberUpdateDto {

    private String loginId;

    @NotEmpty
    @Size(max=20)
    private String password;
    @NotEmpty
    @Size(max=20)
    private String name;

    public MemberUpdateDto() {
    }

    public MemberUpdateDto(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }
}
